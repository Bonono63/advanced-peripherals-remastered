package bmartin127.advancedperipheralsremastered.common.util.fakeplayer;

import bmartin127.advancedperipheralsremastered.AdvancedPeripherals;
import bmartin127.advancedperipheralsremastered.common.util.Pair;
import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import com.mojang.authlib.GameProfile;
import dan200.computercraft.api.turtle.FakePlayer;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.item.*;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.BlockView;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

public class APFakePlayer extends FakePlayer {
    /*
    Highly inspired by https://github.com/SquidDev-CC/plethora/blob/minecraft-1.12/src/main/java/org/squiddev/plethora/gameplay/PlethoraFakePlayer.java
    */
    public static final GameProfile PROFILE = new GameProfile(UUID.fromString("6e483f02-30db-4454-b612-3a167614b276"), "[" + AdvancedPeripherals.MOD_ID + "]");
    private static final Predicate<Entity> collidablePredicate = EntityPredicates.EXCEPT_SPECTATOR;

    private final WeakReference<Entity> owner;
    private ServerPlayerInteractionManager gameMode;

    private BlockPos digPosition;
    private Block digBlock;

    private float currentDamage = 0;

    public APFakePlayer(ServerWorld world, Entity owner, GameProfile profile) {
        super(world, profile != null && profile.isComplete() ? profile : PROFILE);
        if (owner != null) {
            setCustomName(owner.getName());
            this.owner = new WeakReference<>(owner);
        } else {
            this.owner = null;
        }
    }
/*
    @Override
    public void awardStat(@NotNull Stat<?> stat) {
        MinecraftServer server = world.getServer();
        if (server != null && getGameProfile() != PROFILE) {
            PlayerEntity player = server.getPlayerList().getPlayer(getUUID());
            if (player != null)
                player.awardStat(stat);
        }
    }*/

    /*
    @Override
    public boolean canAttack(@NotNull LivingEntity livingEntity) {
        return true;
    }*/

    @Override
    public boolean isSilent() {
        return true;
    }

    @Override
    public void playSound(@NotNull SoundEvent soundIn, float volume, float pitch) {
    }

    private void setState(Block block, BlockPos pos) {

        //TODO: unsure of what this is meant to prevent, I guess we will wait and see...
        if (digPosition != null) {
            gameMode.processBlockBreakingAction(digPosition, PlayerActionC2SPacket.Action.ABORT_DESTROY_BLOCK, Direction.EAST, 320);
        }

        digPosition = pos;
        digBlock = block;
        currentDamage = 0;
    }

    @Override
    public float getEyeHeight(@NotNull EntityPose pose) {
        return 0;
    }

    public Pair<Boolean, String> digBlock(Direction direction) {
        World world = getWorld();
        HitResult hit = findHit(true, false);
        if (hit.getType() == HitResult.Type.MISS)
            return Pair.of(false, "Nothing to break");
        BlockPos pos = new BlockPos((int) hit.getPos().x, (int) hit.getPos().y, (int) hit.getPos().z);
        BlockState state = world.getBlockState(pos);
        Block block = state.getBlock();

        ItemStack tool = getInventory().getMainHandStack();

        if (tool.isEmpty())
            return Pair.of(false, "Cannot dig without tool");


        if (block != digBlock || !pos.equals(digPosition))
            setState(block, pos);

        if (!world.isAir(pos)) {
            if (block == Blocks.BEDROCK || state.getHardness(world, pos) <= -1)
                return Pair.of(false, "Unbreakable block detected");

            if (!(tool.getItem() instanceof MiningToolItem) && !(tool.getItem() instanceof ShearsItem))
                return Pair.of(false, "Item should be digger tool");

            if (tool.getItem() instanceof MiningToolItem toolItem && !toolItem.isSuitableFor(tool, state))
                return Pair.of(false, "Tool cannot mine this block");

            if (tool.getItem() instanceof ShearsItem shearsItem && shearsItem.isSuitableFor(state))
                return Pair.of(false, "Shear cannot mine this block");

            ServerPlayerInteractionManager manager = gameMode;
            float breakSpeed = 0.5f * tool.getMiningSpeedMultiplier(state) / state.getHardness(world, pos) - 0.1f;
            for (int i = 0; i < 10; i++) {
                currentDamage += breakSpeed;

                world.setBlockBreakingInfo(getId(), pos, i);

                if (currentDamage > 9) {
                    world.playSound(null, pos, state.getSoundGroup().getHitSound(), SoundCategory.NEUTRAL, .25f, 1);
                    manager.processBlockBreakingAction(pos, PlayerActionC2SPacket.Action.STOP_DESTROY_BLOCK, direction.getOpposite(), 320);
                    manager.tryBreakBlock(pos);
                    world.setBlockBreakingInfo(getId(), pos, -1);
                    setState(null, null);
                    break;
                }
            }

            return Pair.of(true, "block");
        }

        return Pair.of(false, "Nothing to dig here");
    }

    public ActionResult useOnBlock() {
        return use(true, false);
    }

    public ActionResult useOnEntity() {
        return use(false, true);
    }

    public ActionResult useOnFilteredEntity(Predicate<Entity> filter) {
        return use(false, true, filter);
    }

    public ActionResult useOnSpecificEntity(@NotNull Entity entity, HitResult result) {
        ActionResult simpleInteraction = interact(entity, Hand.MAIN_HAND);
        if (simpleInteraction == ActionResult.SUCCESS) return simpleInteraction;
        if (ForgeHooks.onInteractEntityAt(this, entity, result.getPos(), Hand.MAIN_HAND) != null)
            return ActionResult.FAIL;

        return entity.interactAt(this, result.getPos(), Hand.MAIN_HAND);
    }

    public ActionResult use(boolean skipEntity, boolean skipBlock) {
        return use(skipEntity, skipBlock, null);
    }

    public ActionResult use(boolean skipEntity, boolean skipBlock, @Nullable Predicate<Entity> entityFilter) {
        HitResult hit = findHit(skipEntity, skipBlock, entityFilter);

        if (hit instanceof BlockHitResult blockHit) {

            ItemStack stack = getMainHandStack();
            BlockPos pos = blockHit.getBlockPos();
            PlayerInteractEvent.RightClickBlock event = ForgeHooks.onRightClickBlock(this, Hand.MAIN_HAND, pos, blockHit);
            if (event.isCanceled())
                return event.getCancellationResult();

            if (event.getUseItem() != Event.Result.DENY) {
                ActionResult result = stack.onItemUseFirst(new ItemUsageContext(getWorld(), this, Hand.MAIN_HAND, stack, blockHit));
                if (result != ActionResult.PASS)
                    return result;
            }

            boolean bypass = getMainHandItem().doesSneakBypassUse(getWorld(), pos, this);
            if (getPose() != EntityPose.CROUCHING || bypass || event.getUseBlock() == Event.Result.ALLOW) {
                ActionResult useType = gameMode.useItemOn(this, getWorld(), stack, Hand.MAIN_HAND, blockHit);
                if (event.getUseBlock() != Event.Result.DENY && useType == ActionResult.SUCCESS)
                    return ActionResult.SUCCESS;

            }

            if (stack.isEmpty() || getCooldowns().isOnCooldown(stack.getItem()))
                return ActionResult.PASS;


            if (stack.getItem() instanceof BlockItem blockItem) {
                Block block = blockItem.getBlock();
                if (block instanceof CommandBlock || block instanceof StructureBlock)
                    return ActionResult.FAIL;
            }

            if (event.getUseItem() == Event.Result.DENY)
                return ActionResult.PASS;

            ItemStack copyBeforeUse = stack.copy();
            ActionResult result = stack.useOnBlock(new ItemUsageContext(getWorld(), this, Hand.MAIN_HAND, copyBeforeUse, blockHit));
            if (stack.isEmpty())
                ForgeEventFactory.onPlayerDestroyItem(this, copyBeforeUse, Hand.MAIN_HAND);
            return result;
        } else if (hit instanceof EntityHitResult entityHit) {
            return useOnSpecificEntity(entityHit.getEntity(), entityHit);
        }
        return ActionResult.FAIL;
    }

    public HitResult findHit(boolean skipEntity, boolean skipBlock) {
        return findHit(skipEntity, skipBlock, null);
    }

    @NotNull
    public HitResult findHit(boolean skipEntity, boolean skipBlock, @Nullable Predicate<Entity> entityFilter) {
        EntityAttributeInstance reachAttribute = getAttributeInstance(ReachEntityAttributes.REACH);
        if (reachAttribute == null)
            throw new IllegalArgumentException("How did this happened?");

        double range = reachAttribute.getValue();
        Vec3d origin = new Vec3d(getX(), getY(), getZ());
        Vec3d look = getRotationVector();
        Vec3d target = new Vec3d(origin.x + look.x * range, origin.y + look.y * range, origin.z + look.z * range);
        RaycastContext traceContext = new RaycastContext(origin, target, RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, this);
        Vec3d directionVec = traceContext.getStart().subtract(traceContext.getEnd());
        Direction traceDirection = Direction.getFacing(directionVec.x, directionVec.y, directionVec.z);
        HitResult blockHit;
        if (skipBlock) {
            blockHit = BlockHitResult.createMissed(traceContext.getEnd(), traceDirection, new BlockPos((int) traceContext.getEnd().x, (int) traceContext.getEnd().y, (int) traceContext.getEnd().z));
        } else {
            blockHit = BlockView.raycast(traceContext.getStart(), traceContext.getEnd(), traceContext, (rayTraceContext, blockPos) -> {
                if (getWorld().isAir(blockPos))
                    return null;

                return new BlockHitResult(new Vec3d(blockPos.getX(), blockPos.getY(), blockPos.getZ()), traceDirection, blockPos, false);
            }, rayTraceContext -> BlockHitResult.createMissed(rayTraceContext.getEnd(), traceDirection, new BlockPos((int) rayTraceContext.getEnd().x, (int) rayTraceContext.getEnd().y, (int) rayTraceContext.getEnd().z)));
        }

        if (skipEntity)
            return blockHit;

        List<Entity> entities = getWorld().getEntityLookup(this, getBoundingBox().stretch(look.x * range, look.y * range, look.z * range).expand(1, 1, 1), collidablePredicate);

        LivingEntity closestEntity = null;
        Vec3d closestVec = null;
        double closestDistance = range;
        for (Entity entityHit : entities) {
            if (!(entityHit instanceof LivingEntity) || entityFilter != null && !entityFilter.test(entityHit))
                continue;
            // Add litter bigger that just pick radius
            Box box = entityHit.getBoundingBox().expand(entityHit.getTargetingMargin() + 0.5);
            Optional<Vec3d> clipResult = box.raycast(origin, target);

            if (box.contains(origin)) {
                if (closestDistance >= 0.0D) {
                    closestEntity = (LivingEntity) entityHit;
                    closestVec = clipResult.orElse(origin);
                    closestDistance = 0.0D;
                }
            } else if (clipResult.isPresent()) {
                Vec3d clipVec = clipResult.get();
                double distance = origin.distanceTo(clipVec);

                if (distance < closestDistance || closestDistance == 0.0D) {
                    if (entityHit == entityHit.getRootVehicle() && !entityHit.hasPlayerRider()) {
                        if (closestDistance == 0.0D) {
                            closestEntity = (LivingEntity) entityHit;
                            closestVec = clipVec;
                        }
                    } else {
                        closestEntity = (LivingEntity) entityHit;
                        closestVec = clipVec;
                        closestDistance = distance;
                    }
                }
            }
        }
        if (closestEntity != null && closestDistance <= range && (blockHit.getType() == HitResult.Type.MISS || squaredDistanceTo(blockHit.getPos()) > closestDistance * closestDistance)) {
            return new EntityHitResult(closestEntity, closestVec);
        } else {
            return blockHit;
        }
    }
}
