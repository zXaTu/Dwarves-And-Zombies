package hellwig.daz.scoreboard;

import org.bukkit.ChatColor;

public enum EntryName {
	  ENTRY_0(0, ChatColor.DARK_PURPLE.toString()),
	  ENTRY_1(1, ChatColor.DARK_GRAY.toString()),
	  ENTRY_2(2, ChatColor.BOLD.toString()),
	  ENTRY_3(3, ChatColor.DARK_RED.toString()),
	  ENTRY_4(4, ChatColor.GRAY.toString()),
	  ENTRY_5(5, ChatColor.DARK_GREEN.toString()),
	  ENTRY_6(6, ChatColor.LIGHT_PURPLE.toString()),
	  ENTRY_7(7, ChatColor.UNDERLINE.toString()),
	  ENTRY_8(8, ChatColor.AQUA.toString()),
	  ENTRY_9(9, ChatColor.GOLD.toString()),
	  ENTRY_10(10, ChatColor.STRIKETHROUGH.toString()),
	  ENTRY_11(11, ChatColor.ITALIC.toString()),
	  ENTRY_12(12, ChatColor.YELLOW.toString()),
	  ENTRY_13(13, ChatColor.BLACK.toString()),
	  ENTRY_14(14, ChatColor.MAGIC.toString()),
	  ENTRY_15(15, ChatColor.DARK_AQUA.toString()),
	  ENTRY_16(16, ChatColor.DARK_BLUE.toString()),
	  ENTRY_17(17, ChatColor.WHITE.toString()),
	  ENTRY_18(18, ChatColor.RESET.toString());
	  
	  private final int entry;
	  
	  private final String entryName;
	  
	  EntryName(int entry, String entryName) {
	    this.entry = entry;
	    this.entryName = entryName;
	  }
	  
	  public int getEntry() {
	    return this.entry;
	  }
	  
	  public String getEntryName() {
	    return this.entryName;
	  }
}
