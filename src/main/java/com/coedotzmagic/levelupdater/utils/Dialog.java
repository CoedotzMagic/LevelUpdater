package com.coedotzmagic.levelupdater.utils;

import javax.swing.*;

public class Dialog extends JFrame {
    protected static void showDialog(String message, String title, boolean isErrorMsg) {
        JOptionPane optionPane = new JOptionPane(message, isErrorMsg ? JOptionPane.ERROR_MESSAGE : JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = optionPane.createDialog(title);
        dialog.setModal(false);
        dialog.setVisible(true);
    }

}
