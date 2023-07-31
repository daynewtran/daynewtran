package com.console.component;

import com.console.event.EventMenu;
import com.console.swing.Button1;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import net.miginfocom.swing.MigLayout;

public class Menu1 extends javax.swing.JPanel {

    private EventMenu event;

    public Menu1() {
        initComponents();
        setOpaque(false);
        setLayout(new MigLayout("wrap 1, inset 50", "[fill, 150]10[fill, 100]", "[]15[]"));
    }

    public void initMenu(EventMenu event) {
        this.event = event;
        addMenu(new ImageIcon(getClass().getResource("")), "SẢN PHẨM", 0);        
        addMenu(new ImageIcon(getClass().getResource("")), "KHÁCH HÀNG", 1);
        addMenu(new ImageIcon(getClass().getResource("")), "ĐƠN HÀNG", 2);
        addMenu(new ImageIcon(getClass().getResource("")), "TÀI KHOẢN", 3);
    }

    private void addMenu(Icon icon, String name, int index) {
        Button1 menu = new Button1();
        menu.setHorizontalTextPosition(SwingConstants.CENTER);
        menu.setVerticalTextPosition(SwingConstants.BOTTOM);
        menu.setText(name);
        menu.setIcon(icon);
        menu.setForeground(Color.white);
        //menu.setFont(new Font("", 14, 14));
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                event.selected(index);
            }
        });
        add(menu);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
