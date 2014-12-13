package com.insane.mattercrystals;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;

import com.google.gson.Gson;
import com.insane.mattercrystals.blocks.MCBlocks;
import com.insane.mattercrystals.fundamentals.BasicStack;
import com.insane.mattercrystals.fundamentals.Fundamental;
import com.insane.mattercrystals.fundamentals.FundamentalList;
import com.insane.mattercrystals.fundamentals.Fundamentals;
import com.insane.mattercrystals.handlers.TooltipHandler;
import com.insane.mattercrystals.items.MCItems;
import com.insane.mattercrystals.util.OreDict;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid=MatterCrystals.MODID, name="MatterCrystals", version="0.0.1")
public class MatterCrystals {
	
	public static final String MODID = "MatterCrystals";
	
	@Mod.Instance(MODID)
	public static MatterCrystals instance;
	
	@SidedProxy(clientSide="com.insane.mattercrystals.client.ClientProxy", serverSide="com.insane.mattercrystals.CommonProxy")
	public static CommonProxy proxy;
	
	public static int crystalRenderID;
	
	public static Gson gson = new Gson();
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		MCBlocks.registerBlocks();
		MCItems.registerItems();
		OreDict.registerOreDict();
		proxy.initRenderers();
		
		MinecraftForge.EVENT_BUS.register(new TooltipHandler());
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		FundamentalList.addFundamentalsToStack(new ItemStack(Blocks.cobblestone, 1, OreDictionary.WILDCARD_VALUE), new Fundamental(0,0,0,1,0));
		FundamentalList.addFundamentalsToStack(new ItemStack(Blocks.log, 1, OreDictionary.WILDCARD_VALUE), new Fundamental(8, 0, 0, 0, 0));
		FundamentalList.addFundamentalsToStack(new ItemStack(Blocks.log2, 1, OreDictionary.WILDCARD_VALUE), new Fundamental(8, 0, 0, 0, 0));
		Fundamentals.getCostsFromRecipes(4);
		
	}
	
	public static CreativeTabs tabMC = new CreativeTabs("matterCrystals")
	{
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem()
		{
			return Items.baked_potato;
		}
	};

}