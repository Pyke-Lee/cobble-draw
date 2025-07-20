package io.github.pyke;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public class Main extends JavaPlugin {
    private final Random random = new Random();

    @Override
    public void onEnable() {
        getLogger().info("CobbleDraw Enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("CobbleDraw Disabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player player)) { return true; }

        PlayerInventory inventory = player.getInventory();
        if (inventory.firstEmpty() == -1) {
            player.sendMessage("§cYour inventory is full! Please free up some space.");
            return true;
        }

        int iAmount = 64;

        ItemStack cobble = new ItemStack(Material.COBBLESTONE);
        if (!player.getInventory().containsAtLeast(cobble, iAmount)) {
            player.sendMessage(String.format("§cYou need at least %d cobblestone to draw!", iAmount));
            return true;
        }

        player.getInventory().removeItem(new ItemStack(Material.COBBLESTONE, iAmount));
        ItemStack[] rewards = {
                new ItemStack(Material.DIAMOND, 1),         // 다이아몬드 1개
                new ItemStack(Material.IRON_INGOT, 2),      // 철 2개
                new ItemStack(Material.COBBLESTONE, 16)     // 코블스톤 16개
        };

        ItemStack reward = rewards[random.nextInt(rewards.length)];
        player.getInventory().addItem(reward);
        player.sendMessage("§cYou received " + reward.getAmount() + "x " + reward.getType().name() + "!");
        return true;
    }
}