/*
 *  This file is part of Cubic Chunks Mod, licensed under the MIT License (MIT).
 *
 *  Copyright (c) 2015 contributors
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */
package cubicchunks.network;

import cubicchunks.util.CubeCoords;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketUnloadCube implements IMessage {
	private CubeCoords cubePos;

	public PacketUnloadCube() {}

	public PacketUnloadCube(CubeCoords cubePos) {
		this.cubePos = cubePos;
	}

	@Override
	public void fromBytes(ByteBuf in) {
		this.cubePos = new CubeCoords(in.readInt(), in.readInt(), in.readInt());
	}

	@Override
	public void toBytes(ByteBuf out) {
		out.writeInt(cubePos.getCubeX());
		out.writeInt(cubePos.getCubeY());
		out.writeInt(cubePos.getCubeZ());
	}

	public CubeCoords getCubePos() {
		return cubePos;
	}

	public static class Handler extends AbstractClientMessageHandler<PacketUnloadCube> {
		@Override
		public IMessage handleClientMessage(EntityPlayer player, PacketUnloadCube message, MessageContext ctx) {
			ClientHandler.getInstance().handle(message);
			return null;
		}
	}
}
