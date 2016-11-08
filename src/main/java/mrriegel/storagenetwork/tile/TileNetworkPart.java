package mrriegel.storagenetwork.tile;

import mrriegel.limelib.tile.CommonTile;
import mrriegel.limelib.util.GlobalBlockPos;
import net.minecraft.nbt.NBTTagCompound;

public class TileNetworkPart extends CommonTile implements INetworkPart {

	protected GlobalBlockPos corePos;

	@Override
	public GlobalBlockPos getPosition() {
		return new GlobalBlockPos(getPos(), getWorld());
	}

	@Override
	public TileNetworkCore getNetworkCore() {
		if (corePos != null)
			return (TileNetworkCore) corePos.getTile(worldObj);
		return null;
	}

	@Override
	public void setNetworkCore(TileNetworkCore core) {
		if (core == null)
			corePos = null;
		else
			corePos = new GlobalBlockPos(core.getPos(), core.getWorld().provider.getDimension());
		markDirty();
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		corePos = GlobalBlockPos.loadGlobalPosFromNBT(compound.getCompoundTag("corePos"));
		super.readFromNBT(compound);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		if (corePos != null) {
			NBTTagCompound nbt = new NBTTagCompound();
			corePos.writeToNBT(nbt);
			compound.setTag("corePos", nbt);
		}
		return super.writeToNBT(compound);
	}

}
