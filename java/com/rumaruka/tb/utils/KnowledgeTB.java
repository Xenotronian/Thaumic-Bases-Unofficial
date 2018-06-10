package com.rumaruka.tb.utils;

import com.rumaruka.tb.api.ResearchAddendumBuilder;
import com.rumaruka.tb.api.ResearchEntryBuilder;
import com.rumaruka.tb.api.ResearchStageBuilder;
import com.rumaruka.tb.core.TBCore;
import com.rumaruka.tb.init.TBBlocks;
import com.rumaruka.tb.init.TBItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.api.capabilities.IPlayerKnowledge;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchEntry;
import thaumcraft.api.research.ResearchStage;
import thaumcraft.common.lib.CommandThaumcraft;
import thaumcraft.common.lib.research.ResearchManager;

import java.lang.reflect.Method;
public class KnowledgeTB {

    public static final OnetimeCaller init = new OnetimeCaller(KnowledgeTB::$init);
    public static final OnetimeCaller clInit = new OnetimeCaller(KnowledgeTB::$);

    @SubscribeEvent
    public void commandEvent(CommandEvent ce)
    {
        if(ce.getCommand() instanceof CommandThaumcraft && ce.getParameters().length > 0 && ce.getParameters()[0].equalsIgnoreCase("reload"))
        {
            new Thread(() ->
            {
                while(TBCore.RES_CAT.research.containsKey("TB_FIRST"))
                    try
                    {
                        Thread.sleep(10L);
                    } catch(InterruptedException e)
                    {
                        e.printStackTrace();
                    }

                $init();
            }).start();
        }
    }

    private static void $init()
    {
        new REB().setBaseInfo("TB.FIRST", "thaumicbases", 0, 0, new ResourceLocation(TBCore.modid, "textures/thaumonomicon/bases.png")).setMeta(ResearchEntry.EnumResearchMeta.HIDDEN, ResearchEntry.EnumResearchMeta.SPIKY).setStages(new RSB().setText("research_stage." + TBCore.modid + ":thaumbases.1").setKnow(new ResearchStage.Knowledge(IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, TBCore.RES_CAT, 1)).build(), new RSB().setText("research_stage." + TBCore.modid + ":thaumicbases.2").build()).setParents("FIRSTSTEPS").buildAndRegister();

        new REB().setBaseInfo("TB.ALCHEMY", "tbalchemy", 0, 2, new ItemStack(TBItems.thauminite_ingot)).setStages(new RSB().setText("research_stage." + TBCore.modid + ":tbalchemy.1").setConsumedItems(new ItemStack(ItemsTC.ingots,1,0)).build(), new RSB().setText("research_stage." + TBCore.modid + ":tbalchemy.2").setRecipes("TB.Thauminite").build()).setParents("TB.FIRST").buildAndRegister();
        new REB().setBaseInfo("TB.FLUID", "tbfluid", 2, 2, new ItemStack(TBItems.pyrobucket)).setStages(new RSB().setText("research_stage." + TBCore.modid + ":tbfluid.1").setKnow(new ResearchStage.Knowledge(IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, ResearchCategories.getResearchCategory("ALCHEMY"), 1)).setConsumedItems(new ItemStack(Items.BUCKET)).build(), new RSB().setText("research_stage." + TBCore.modid + ":tbfluid.2").setRecipes("TB.lavaToPyro").build()).setParents("TB.FIRST","TB.ALCHEMY").buildAndRegister();


        new REB().setBaseInfo("TB.DECO", "tbdeco", -2, 4, new ItemStack(TBBlocks.olddiamond)).setStages(new RSB().setText("research_stage." + TBCore.modid + ":tbdeco.1").setRecipes("TB.DecoOldCobble","TB.DecoOldCobbleMossy","TB.DecoOldGravel","TB.DecoOldBrick","TB.DecoOldLapis","TB.DecoOldIron","TB.DecoOldGold","TB.DecoOldDiamond").build()).setParents("TB.FIRST").buildAndRegister();
        new REB().setBaseInfo("TB.DECO2", "tbdeco2", -2, 6, new ItemStack(TBBlocks.dustblock)).setMeta(ResearchEntry.EnumResearchMeta.ROUND).setStages(new RSB().setText("research_stage." + TBCore.modid + ":tbdeco2.1").setRecipes("thaumicbases:quicksilverblock","thaumicbases:quicksilverbrick","thaumicbases:irongreatwood","thaumicbases:eldritchark","TB.SMB","TB.SMBReturn").build()).setParents("TB.DECO").buildAndRegister();

        new REB().setBaseInfo("TB.MACERATOR", "tbmacerator", 0, 4, new ItemStack(Blocks.GRAVEL)).setStages(new RSB().setText("research_stage." + TBCore.modid + ":tbmacerator.1").setKnow(new ResearchStage.Knowledge(IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, ResearchCategories.getResearchCategory("ALCHEMY"), 1)).build(), new RSB().setText("research_stage." + TBCore.modid + ":tbmacerator.2").setRecipes("TB.gravelToFlint","TB.cobbleToSand","TB.sandToNuggetGold","TB.woolToString","TB.rodToPower").build()).setParents("TB.ALCHEMY").buildAndRegister();
        new REB().setBaseInfo("TB.AdvMACERATOR", "tbadvmacerator", 2,4 , new ItemStack(Blocks.GRAVEL), new ItemStack(Blocks.SAND)).setStages(new RSB().setText("research_stage." + TBCore.modid + ":tbadvmacerator.1").setKnow(new ResearchStage.Knowledge(IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, ResearchCategories.getResearchCategory("ALCHEMY"), 1)).build(), new RSB().setText("research_stage." + TBCore.modid + ":tbadvmacerator.2").setRecipes("TB.gravelToFlintAdv","TB.cobbleToSandAdv","TB.sandToNuggetGoldAdv","TB.woolToStringAdv","TB.rodToPowerAdv").build()).setParents("TB.MACERATOR").buildAndRegister();
        new REB().setBaseInfo("TB.EXCHANG", "tbexchang", 0,6 , new ItemStack(ItemsTC.quicksilver)).setStages(new RSB().setText("research_stage." + TBCore.modid + ":tbexchang.1").setKnow(new ResearchStage.Knowledge(IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, ResearchCategories.getResearchCategory("ALCHEMY"), 1)).build(), new RSB().setText("research_stage." + TBCore.modid + ":tbexchang.2").setRecipes("TB.powderToRod","TB.saplingToAmber","TB.silverToQuicksilver").build()).setParents("TB.MACERATOR").buildAndRegister();
        new REB().setBaseInfo("TB.DOUBLE", "tbdouble", 0,8 , new ItemStack(ItemsTC.salisMundus)).setStages(new RSB().setText("research_stage." + TBCore.modid + ":tbdouble.1").setConsumedItems(new ItemStack(ItemsTC.salisMundus)).build(), new RSB().setText("research_stage." + TBCore.modid + ":tbexchang.2").setRecipes("TB.doubleMundus").build()).setParents("TB.EXCHANG").buildAndRegister();

        new REB().setBaseInfo("TB.PLANT", "tbplant", 4, 2, new ItemStack(TBItems.sweedseed)).setStages(new RSB().setText("research_stage." + TBCore.modid + ":tbplant.1").setConsumedItems(new ItemStack(Items.WHEAT,1,0)).build(), new RSB().setText("research_stage." + TBCore.modid + ":tbplant.2").setRecipes("TB.seedToSweed").build()).setParents("TB.FLUID").buildAndRegister();
        new REB().setBaseInfo("TB.PLANT1", "tbplant1", 6, 2, new ItemStack(TBItems.plaxseed)).setMeta(ResearchEntry.EnumResearchMeta.ROUND).setStages(new RSB().setText("research_stage." + TBCore.modid + ":tbplant1.1").setConsumedItems(new ItemStack(Items.WHEAT_SEEDS,1,0)).build(), new RSB().setText("research_stage." + TBCore.modid + ":tbplant1.2").setRecipes("TB.sweedToPlax").build(),  new RSB().setText("research_stage." + TBCore.modid + ":tbplant1.3").setConsumedItems(new ItemStack(Blocks.RED_FLOWER)).setRecipes("TB.aurelia").build()).setParents("TB.PLANT").buildAndRegister();
        new REB().setBaseInfo("TB.CACTUS", "tbcactus", 8, 2, new ItemStack(TBBlocks.rainbowcactus)).setMeta(ResearchEntry.EnumResearchMeta.ROUND).setStages(new RSB().setText("research_stage." + TBCore.modid + ":tbcactus.1").setConsumedItems(new ItemStack(Blocks.CACTUS,1,0)).build(), new RSB().setText("research_stage." + TBCore.modid + ":tbcactus.2").setRecipes("TB.catusToRC").build()).setParents("TB.PLANT1").buildAndRegister();
        new REB().setBaseInfo("TB.TREE", "tbtree", 10, 2, new ItemStack(TBBlocks.goldensapling)).setStages(new RSB().setText("research_stage." + TBCore.modid + ":tbtree.1").setConsumedItems(new ItemStack(Blocks.SAPLING,1,0)).build(), new RSB().setText("research_stage." + TBCore.modid + ":tbtree.2").setRecipes("TB.goldenSapling").build()).setParents("TB.CACTUS").buildAndRegister();
        new REB().setBaseInfo("TB.TREE1", "tbtree1", 10, 0, new ItemStack(TBBlocks.endersapling)).setStages(new RSB().setText("research_stage." + TBCore.modid + ":tbtree.1").setConsumedItems(new ItemStack(Blocks.SAPLING,1,0)).build(), new RSB().setText("research_stage." + TBCore.modid + ":tbtree1.2").setRecipes("TB.enderSapling").build()).setParents("TB.TREE").buildAndRegister();
        new REB().setBaseInfo("TB.TREE2", "tbtree2", 10, 4, new ItemStack(TBBlocks.nethersapling)).setStages(new RSB().setText("research_stage." + TBCore.modid + ":tbtree.1").setConsumedItems(new ItemStack(Blocks.SAPLING,1,0)).build(), new RSB().setText("research_stage." + TBCore.modid + ":tbtree2.2").setRecipes("TB.netherSapling").setWarp(3).build()).setParents("TB.TREE").buildAndRegister();

        new REB().setBaseInfo("TB.MACHANISM", "tbmachanism", 0, -2, new ItemStack(TBBlocks.overchanter)).setStages(new RSB().setText("research_stage." + TBCore.modid + ":tbmachanism.1").setConsumedItems(new ItemStack(Blocks.CRAFTING_TABLE)).build(), new RSB().setText("research_stage." + TBCore.modid + ":tbmachanism.2").setRecipes("TB.overchanter").build()).setParents("TB.FIRST").buildAndRegister();


    }






    private static class RAB extends ResearchAddendumBuilder
    {
    }

    private static class RSB extends ResearchStageBuilder
    {
    }

    private static class REB extends ResearchEntryBuilder
    {
        public ResearchEntryBuilder setBaseInfo(String key, String name, int x, int y, Object... icons)
        {
            return super.setBaseInfo(key, "THAUMICBASES", "research_name." + TBCore.modid + ":" + name, x, y, icons);
        }
    }
    private static Method addResearchToCategory = null;
    public static void addResearchToCategory(ResearchEntry ri) {
        if(addResearchToCategory == null)
            try
            {
                addResearchToCategory = ResearchManager.class.getDeclaredMethod("addResearchToCategory", ResearchEntry.class);
                addResearchToCategory.setAccessible(true);
            } catch(NoSuchMethodException | SecurityException e)
            {

            }

        try
        {
            addResearchToCategory.invoke(null, ri);
        } catch(Throwable e)
        {

        }

    }
    private static void $() {
    }
}
