// --------------------------------------------------------------------------------------------------
//  Copyright (c) 2016 Microsoft Corporation
//  
//  Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
//  associated documentation files (the "Software"), to deal in the Software without restriction,
//  including without limitation the rights to use, copy, modify, merge, publish, distribute,
//  sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
//  furnished to do so, subject to the following conditions:
//  
//  The above copyright notice and this permission notice shall be included in all copies or
//  substantial portions of the Software.
//  
//  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
//  NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
//  NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
//  DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
// --------------------------------------------------------------------------------------------------

package com.microsoft.Malmo.MissionHandlers;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.ByteBufUtils;

import com.microsoft.Malmo.MalmoMod;
import com.microsoft.Malmo.MalmoMod.IMalmoMessageListener;
import com.microsoft.Malmo.MalmoMod.MalmoMessageType;
import com.microsoft.Malmo.MissionHandlerInterfaces.IRewardProducer;
import com.microsoft.Malmo.Schemas.BlockOrItemSpecWithReward;
import com.microsoft.Malmo.Schemas.MissionInit;
import com.microsoft.Malmo.Schemas.RewardForCollectingItem;

public class RewardForCollectingItemImplementation extends RewardForItemBase implements IRewardProducer, IMalmoMessageListener
{
    private RewardForCollectingItem params;

    @Override
    public void onMessage(MalmoMessageType messageType, Map<String, String> data) 
    {
        String bufstring = data.get("message");
        ByteBuf buf = Unpooled.copiedBuffer(DatatypeConverter.parseBase64Binary(bufstring));
        ItemStack itemStack = ByteBufUtils.readItemStack(buf);
        if (itemStack != null && itemStack.getItem() != null)
        {
            accumulateReward(this.params.getDimension(), itemStack);
        }
        else
        {
            System.out.println("Error - couldn't understand the itemstack we received.");
        }
    }
    
    public static class GainItemEvent extends Event
    {
        public final ItemStack stack;

        public GainItemEvent(ItemStack stack)
        {
            this.stack = stack;
        }
    }

    @Override
    public boolean parseParameters(Object params) {
        if (params == null || !(params instanceof RewardForCollectingItem))
            return false;

        // Build up a map of rewards per item:
        this.params = (RewardForCollectingItem) params;
        for (BlockOrItemSpecWithReward is : this.params.getItem())
            addItemSpecToRewardStructure(is);

        return true;
    }

    @SubscribeEvent
    public void onGainItem(GainItemEvent event)
    {
        if (event.stack != null)
        {
            accumulateReward(this.params.getDimension(), event.stack);
        }
    }

    @SubscribeEvent
    public void onPickupItem(EntityItemPickupEvent event)
    {
        if (event.getItem() != null && event.getEntityPlayer() instanceof EntityPlayerMP )
        {
            // This event is received on the server side, so we need to pass it to the client.
            sendItemStackToClient((EntityPlayerMP)event.getEntityPlayer(), MalmoMessageType.SERVER_COLLECTITEM, event.getItem().getItem());
        }
    }

    @Override
    public void prepare(MissionInit missionInit)
    {
        super.prepare(missionInit);
        MinecraftForge.EVENT_BUS.register(this);
        MalmoMod.MalmoMessageHandler.registerForMessage(this, MalmoMessageType.SERVER_COLLECTITEM);
    }

    @Override
    public void getReward(MissionInit missionInit, MultidimensionalReward reward)
    {
        super.getReward(missionInit, reward);
    }

    @Override
    public void cleanup()
    {
        super.cleanup();
        MinecraftForge.EVENT_BUS.unregister(this);
        MalmoMod.MalmoMessageHandler.deregisterForMessage(this, MalmoMessageType.SERVER_COLLECTITEM);
    }
}
