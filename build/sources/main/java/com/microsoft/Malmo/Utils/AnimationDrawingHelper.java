package com.microsoft.Malmo.Utils;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class AnimationDrawingHelper extends BlockDrawingHelper
{
    Vec3d origin = new Vec3d(0,0,0);
    Set<BlockPos> drawing = new HashSet<BlockPos>();
    Set<BlockPos> previousFrame;
    Vec3d minPos;
    Vec3d maxPos;

    @Override
    public void beginDrawing(World w)
    {
        this.previousFrame = new HashSet<BlockPos>(this.drawing);
        this.drawing = new HashSet<BlockPos>();
        this.minPos = null;
        this.maxPos = null;
        super.beginDrawing(w);
    }

    public void endDrawing(World w)
    {
        super.endDrawing(w);
    }

    public void clearPrevious(World w)
    {
        if (this.previousFrame != null)
        {
            for (BlockPos pos : this.previousFrame)
            {
                w.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
            }
        }
        this.previousFrame = null;
    }

    public Vec3d getMin()
    {
        return this.minPos;
    }

    public Vec3d getMax()
    {
        return this.maxPos;
    }

    public Vec3d getOrigin()
    {
        return this.origin;
    }

    public void setOrigin(Vec3d org)
    {
        this.origin = org;
    }

    @Override
    public void setBlockState(World w, BlockPos pos, XMLBlockState state)
    {
        BlockPos offsetPos = pos.add(this.origin.x, this.origin.y, this.origin.z);
        this.drawing.add(offsetPos);
        this.previousFrame.remove(offsetPos);
        if (this.minPos == null)
            this.minPos = new Vec3d(offsetPos.getX() - 0.5, offsetPos.getY(), offsetPos.getZ() - 0.5);
        else
        {
            double x = Math.min(this.minPos.x, offsetPos.getX() - 0.5);
            double y = Math.min(this.minPos.y, offsetPos.getY() - 0.5);
            double z = Math.min(this.minPos.z, offsetPos.getZ() - 0.5);
            if (x != this.minPos.x || y != this.minPos.y || z != this.minPos.z)
                this.minPos = new Vec3d(x,y,z);
        }
        if (this.maxPos == null)
            this.maxPos = new Vec3d(offsetPos.getX() + 0.5, offsetPos.getY() + 1, offsetPos.getZ() + 0.5);
        else
        {
            double x = Math.max(this.maxPos.x, offsetPos.getX() + 0.5);
            double y = Math.max(this.maxPos.y, offsetPos.getY() + 0.5);
            double z = Math.max(this.maxPos.z, offsetPos.getZ() + 0.5);
            if (x != this.maxPos.x || y != this.maxPos.y || z != this.maxPos.z)
                this.maxPos = new Vec3d(x,y,z);
        }
        super.setBlockState(w, offsetPos, state);
    }

    @Override
    public void clearEntities(World w, double x1, double y1, double z1, double x2, double y2, double z2)
    {
        super.clearEntities(w, x1+this.origin.x, y1+this.origin.y, z1+this.origin.z, x2+this.origin.x, y2+this.origin.y, z2+this.origin.z);
    }

    @Override
    protected EntityItem createItem(ItemStack stack, double x, double y, double z, World w, boolean centreItem)
    {
        return super.createItem(stack, x+this.origin.x, y+this.origin.y, z+this.origin.z, w, centreItem);
    }

    @Override
    protected void positionEntity( Entity entity, double x, double y, double z, float yaw, float pitch )
    {
        super.positionEntity(entity, x+this.origin.x, y+this.origin.y, z+this.origin.z, yaw, pitch);
    }
}