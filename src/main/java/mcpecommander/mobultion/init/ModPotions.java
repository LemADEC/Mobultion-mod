package mcpecommander.mobultion.init;

import mcpecommander.mobultion.potion.PotionFreeze;
import mcpecommander.mobultion.potion.PotionHypnotize;
import mcpecommander.mobultion.potion.PotionJokerness;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModPotions {
	@GameRegistry.ObjectHolder("mobultion:hypnotize_potion")
	public static PotionHypnotize potionHypnotize;
	@GameRegistry.ObjectHolder("mobultion:freeze_potion")
	public static PotionFreeze potionFreeze;
	@GameRegistry.ObjectHolder("mobultion:jokerness_potion")
	public static PotionJokerness potionJokerness;
	
	public static void init(){
		potionHypnotize = new PotionHypnotize();
		potionFreeze = new PotionFreeze();
		potionJokerness = new PotionJokerness();
	}
}
