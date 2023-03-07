/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Calculations.Calculations;

import XmlWrite.Reservations;
import XmlWrite.TableXmlWrite;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import objects.Table;

/**
 *
 * @author Amr Ashraf
 */
public class ClientGui3 extends javax.swing.JFrame {
    List<Table> alltables ;
    String clientname=null;
    String order = " ";
    int indix;
    double maincost=0;
    double appertizercost=0;
    double desertcost=0;
    
    ArrayList<String>mainDish = new ArrayList<>();
    ArrayList<String>appetizerDish = new ArrayList<>();
    ArrayList<String>desertDish = new ArrayList<>();

    /**
     * Creates new form ClientGui3
     */
    public ClientGui3(List<Table> x , String y ,int i ,ArrayList<String> a , ArrayList<String> b ,ArrayList<String> c ,String s) throws JAXBException {
        initComponents();
        alltables = x;
        clientname=y;
        order=s;
        indix = i;
        mainDish =a;
        appetizerDish=b;
        desertDish=c;
    calculate();

     displayReceipt();
        
        WriteXml();
        
    }
    
    public void WriteXml() throws JAXBException{
        JAXBContext jax = JAXBContext.newInstance(Reservations.class);
      Unmarshaller unm= jax.createUnmarshaller();               
        Reservations r = (Reservations) unm.unmarshal(new File ("Reservation.xml"));
         Reservations s=  r;
        TableXmlWrite t2=new TableXmlWrite();
        t2.setName(clientname);
        t2.setNumber(alltables.get(indix).getNumber());
        t2.setOrder(order);
        t2.setPaid(maincost+appertizercost+desertcost);   ///saved corectly
        
        s.getTables().getTableList().add(t2);
        JAXBContext jax5=JAXBContext.newInstance(Reservations.class);
        Marshaller mmm = jax5.createMarshaller();
        mmm.marshal(s,new File("Reservation.xml"));
        
        
    }
    
   
    public void displayReceipt() throws JAXBException 
    {
        int r=mainDish.size()/2;
        int x=0,y=1;
        DefaultTableModel m = (DefaultTableModel) jTable1.getModel();
        for (int i=0 ; i<r ; i++)
        {
            double c=Calculations.getCostOfDish(mainDish.get(x))* Integer.parseInt(mainDish.get(y));
             m.addRow(new Object[]{mainDish.get(x),Calculations.getCostOfDish(mainDish.get(x)),mainDish.get(y),c,"15%",Calculations.calculateMain(mainDish.get(x), Integer.parseInt(mainDish.get(y)))});
             x+=2;
             y+=2;
        }
        r=appetizerDish.size()/2;
        x=0;
        y=1;
       for (int i=0 ; i<r ; i++)
        {
            double c=Calculations.getCostOfDish(appetizerDish.get(x))* Integer.parseInt(appetizerDish.get(y));
             m.addRow(new Object[]{appetizerDish.get(x),Calculations.getCostOfDish(appetizerDish.get(x)),appetizerDish.get(y),c,"10%",Calculations.calculateAppetizer(appetizerDish.get(x), Integer.parseInt(appetizerDish.get(y)))});
             x+=2;
             y+=2;
        }
       r=desertDish.size()/2;
        x=0;
        y=1;
       for (int i=0 ; i<r ; i++)
        {
            double c=Calculations.getCostOfDish(desertDish.get(x))* Integer.parseInt(desertDish.get(y));
             m.addRow(new Object[]{desertDish.get(x),Calculations.getCostOfDish(desertDish.get(x)),desertDish.get(y),c,"20%",Calculations.calculateDesert(desertDish.get(x), Integer.parseInt(desertDish.get(y)))});
             x+=2;
             y+=2;
        }
       double TC=maincost+appertizercost+desertcost;
        m.addRow(new Object[]{});
        m.addRow(new Object[]{});
        m.addRow(new Object[]{"Total Cost = ",TC });
    }
    
    public void calculate() throws JAXBException
    {
        int r=mainDish.size()/2;
        int x=0,y=1;
        for (int i=0 ; i<r ; i++)
        {
            maincost+=Calculations.calculateMain(mainDish.get(x), Integer.parseInt(mainDish.get(y)));
            x+=2;
            y+=2;
        }
        
        r=appetizerDish.size()/2;
        x=0;
        y=1;
        for (int i=0 ; i<r ; i++)
        {
            appertizercost+=Calculations.calculateAppetizer(appetizerDish.get(x), Integer.parseInt(appetizerDish.get(y)));
            x+=2;
            y+=2;
        }
        
        
         r=desertDish.size()/2;
        x=0;
        y=1;
        for (int i=0 ; i<r ; i++)
        {
            desertcost+=Calculations.calculateDesert(desertDish.get(x), Integer.parseInt(desertDish.get(y)));
            x+=2;
            y+=2;
        }
        
    }

    private ClientGui3() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "       Dish", " Cost of one dish", "      Number", "Cost With out Taxes", "Precentage of Taxes", "Cost with taxes"
            }
        ));
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(5).setResizable(false);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 83, 790, 390));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("for any complaint, contact us ");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setText(" Receipt");

        jButton2.setForeground(new java.awt.Color(0, 153, 255));
        jButton2.setText("mohamedkamaleskander@gmail.com");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setForeground(new java.awt.Color(0, 153, 255));
        jButton3.setText("amrashraf759@gmail.com ");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/icons8-rating-64.png"))); // NOI18N
        jButton4.setText("Rating");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/icons8-back-64.png"))); // NOI18N
        jButton1.setText("Leave");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addGap(36, 36, 36)))
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton4)
                            .addComponent(jButton1)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(417, 417, 417)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 9, Short.MAX_VALUE)))
                .addGap(51, 51, 51))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, 620));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Login l=null;
        try {
            l = new Login();
        } catch (JAXBException ex) {
            Logger.getLogger(ClientGui3.class.getName()).log(Level.SEVERE, null, ex);
        }
        l.setVisible(true);
        dispose();
         JOptionPane.showMessageDialog(rootPane, "we hope you enjoyed our service ");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        Desktop browser = Desktop.getDesktop();
        try {
            browser.browse(new URI("https://mail.google.com/mail/u/0/#search/mohamedkamaleskander%40gmail.com?compose=new"));
            // TODO add your handling code here:
        } catch (URISyntaxException ex) {
            Logger.getLogger(ClientGui3.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientGui3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        Desktop browser = Desktop.getDesktop();
        try {
            browser.browse(new URI("https://mail.google.com/mail/u/1/#search/amrashraf759%40gmail.com?compose=new"));
            // TODO add your handling code here:
        } catch (URISyntaxException ex) {
            Logger.getLogger(ClientGui3.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientGui3.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        RatingGui r = new RatingGui(clientname, alltables.get(indix).getNumber());
        r.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
