package hellwig.daz.plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemBuilder {
	
	private ItemStack is;
	  
	  public ItemBuilder(Material m) {
	    this(m, 1);
	  }
	  
	  public ItemBuilder(ItemStack is) {
	    this.is = is;
	  }
	  
	  public ItemBuilder(Material m, int amount) {
	    this.is = new ItemStack(m, amount);
	  }
	  
	  public ItemBuilder(ItemStack is, int amount) {
		  this.is = is;
		  this.is.setAmount(amount);
	  }
	  
	  public ItemBuilder clone() {
	    return new ItemBuilder(this.is);
	  }
	  
	  @SuppressWarnings("deprecation")
	public ItemBuilder setDurability(int dur) {
	    this.is.setDurability((short)dur);
	    return this;
	  }
	  
	  public ItemBuilder setName(String name) {
	    ItemMeta im = this.is.getItemMeta();
	    im.setDisplayName(name);
	    this.is.setItemMeta(im);
	    return this;
	  }
	  
	  public ItemBuilder addUnsafeEnchantment(Enchantment ench, int level) {
	    this.is.addUnsafeEnchantment(ench, level);
	    return this;
	  }
	  
	  public ItemBuilder removeEnchantment(Enchantment ench) {
	    this.is.removeEnchantment(ench);
	    return this;
	  }
	  
	  @SuppressWarnings("deprecation")
	public ItemBuilder setSkullOwner(String owner) {
	    try {
	      SkullMeta im = (SkullMeta)this.is.getItemMeta();
	      im.setOwner(owner);
	      this.is.setItemMeta((ItemMeta)im);
	    } catch (ClassCastException classCastException) {}
	    return this;
	  }
	  
	  public ItemBuilder addEnchant(Enchantment ench, int level) {
	    ItemMeta im = this.is.getItemMeta();
	    im.addEnchant(ench, level, true);
	    this.is.setItemMeta(im);
	    return this;
	  }
	  
	  public ItemBuilder addEnchantments(Map<Enchantment, Integer> enchantments) {
	    this.is.addEnchantments(enchantments);
	    return this;
	  }
	  
	  public ItemBuilder setLore(String... lore) {
	    ItemMeta im = this.is.getItemMeta();
	    im.setLore(Arrays.asList(lore));
	    this.is.setItemMeta(im);
	    return this;
	  }
	  
	  public ItemBuilder setItemFlag(ItemFlag flag) {
	    ItemMeta im = this.is.getItemMeta();
	    im.addItemFlags(new ItemFlag[] { flag });
	    this.is.setItemMeta(im);
	    return this;
	  }
	  
	  public ItemBuilder setLore(List<String> lore) {
	    ItemMeta im = this.is.getItemMeta();
	    im.setLore(lore);
	    this.is.setItemMeta(im);
	    return this;
	  }
	  
	  public ItemBuilder removeLoreLine(String line) {
	    ItemMeta im = this.is.getItemMeta();
	    List<String> lore = new ArrayList<>(im.getLore());
	    if (!lore.contains(line))
	      return this; 
	    lore.remove(line);
	    im.setLore(lore);
	    this.is.setItemMeta(im);
	    return this;
	  }
	  
	  public ItemBuilder removeLoreLine(int index) {
	    ItemMeta im = this.is.getItemMeta();
	    List<String> lore = new ArrayList<>(im.getLore());
	    if (index < 0 || index > lore.size())
	      return this; 
	    lore.remove(index);
	    im.setLore(lore);
	    this.is.setItemMeta(im);
	    return this;
	  }
	  
	  public ItemBuilder addLoreLine(String line) {
	    ItemMeta im = this.is.getItemMeta();
	    List<String> lore = new ArrayList<>();
	    if (im.hasLore())
	      lore = new ArrayList<>(im.getLore()); 
	    lore.add(line);
	    im.setLore(lore);
	    this.is.setItemMeta(im);
	    return this;
	  }
	  
	  public ItemBuilder addLoreLine(String line, int pos) {
	    ItemMeta im = this.is.getItemMeta();
	    List<String> lore = new ArrayList<>(im.getLore());
	    lore.set(pos, line);
	    im.setLore(lore);
	    this.is.setItemMeta(im);
	    return this;
	  }
	  
	  public ItemBuilder setLeatherArmorColor(Color color) {
	    try {
	      LeatherArmorMeta im = (LeatherArmorMeta)this.is.getItemMeta();
	      im.setColor(color);
	      this.is.setItemMeta((ItemMeta)im);
	    } catch (ClassCastException classCastException) {}
	    return this;
	  }
	  
	  public ItemStack toItemStack() {
	    return this.is;
	  }
}
