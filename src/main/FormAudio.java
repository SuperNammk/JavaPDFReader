/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package main;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.swing.JOptionPane;

/**
 *
 * @author hnam1
 */
public class FormAudio extends javax.swing.JFrame {

    public String fileName;
    private File file;
    private AudioInputStream audioStream;
    private Clip clip;

    /**
     * Creates new form FormAudio
     * @param audioFileName
     */
    public FormAudio(String audioFileName) {
        initComponents();
        fileName = audioFileName;
        try {
            file = new File(fileName);
            audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            jbtnPlay.setEnabled(false);
            jbtnStop.setEnabled(true);
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi mở âm thanh");
            this.dispose();
            
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbtnPlay = new javax.swing.JButton();
        jbtnStop = new javax.swing.JButton();
        jbtnReset = new javax.swing.JButton();
        jbtnQuit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jbtnPlay.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jbtnPlay.setText(">");
        jbtnPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnPlayActionPerformed(evt);
            }
        });

        jbtnStop.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jbtnStop.setText("I I");
        jbtnStop.setEnabled(false);
        jbtnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnStopActionPerformed(evt);
            }
        });

        jbtnReset.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jbtnReset.setText("I <");
        jbtnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnResetActionPerformed(evt);
            }
        });

        jbtnQuit.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jbtnQuit.setForeground(new java.awt.Color(255, 51, 51));
        jbtnQuit.setText("X");
        jbtnQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnQuitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbtnPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbtnStop, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbtnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbtnQuit, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnStop, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnQuit, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnPlayActionPerformed
        clip.start();
        jbtnPlay.setEnabled(false);
        jbtnStop.setEnabled(true);
    }//GEN-LAST:event_jbtnPlayActionPerformed

    private void jbtnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnStopActionPerformed
        clip.stop();
        jbtnPlay.setEnabled(true);
        jbtnStop.setEnabled(false);
    }//GEN-LAST:event_jbtnStopActionPerformed

    private void jbtnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnResetActionPerformed
        clip.setMicrosecondPosition(0);
    }//GEN-LAST:event_jbtnResetActionPerformed

    private void jbtnQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnQuitActionPerformed
        clip.close();
        this.dispose();
    }//GEN-LAST:event_jbtnQuitActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtnPlay;
    private javax.swing.JButton jbtnQuit;
    private javax.swing.JButton jbtnReset;
    private javax.swing.JButton jbtnStop;
    // End of variables declaration//GEN-END:variables
}
