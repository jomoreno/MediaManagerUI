/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediamanager.test;

import java.awt.BorderLayout;

import java.awt.Color;

import java.awt.Dimension;



import javax.swing.JDialog;

import javax.swing.JPanel;



public class JDialogBack extends JDialog

{

  public static void main(String[] args)

  {

    JPanel p1 = new JPanel();

    p1.setBackground(Color.red);

    JPanel p2 = new JPanel();

    p2.setBackground(Color.blue);

    JPanel p3 = new JPanel();

    p3.setBackground(Color.green);

    

    JDialogBack t = new JDialogBack();

    t.getContentPane().setLayout(null);

    //t.getContentPane().add(p1, BorderLayout.CENTER);

    //t.getContentPane().add(p2, BorderLayout.EAST);

    //t.getContentPane().add(p3, BorderLayout.SOUTH);

    

    t.getContentPane().setBackground(Color.yellow);

    

    t.setSize(new Dimension(300,300));

    t.setVisible(true);

  }

}