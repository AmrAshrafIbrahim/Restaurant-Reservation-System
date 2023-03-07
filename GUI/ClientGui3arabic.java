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
public class ClientGui3arabic extends javax.swing.JFrame {
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
    public ClientGui3arabic(List<Table> x , String y ,int i ,ArrayList<String> a , ArrayList<String> b ,ArrayList<String> c ,String s) throws JAXBException {
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
             m.addRow(new Object[]{Calculations.calculateMain(mainDish.get(x), Integer.parseInt(mainDish.get(y))),"15%",c,mainDish.get(y),Calculations.getCostOfDish(mainDish.get(x)),mainDish.get(x)});
             x+=2;
             y+=2;
        }
        r=appetizerDish.size()/2;
        x=0;
        y=1;
       for (int i=0 ; i<r ; i++)
        {
            double c=Calculations.getCostOfDish(appetizerDish.get(x))* Integer.parseInt(appetizerDish.get(y));
             m.addRow(new Object[]{Calculations.calculateAppetizer(appetizerDish.get(x), Integer.parseInt(appetizerDish.get(y))),"10%",c,appetizerDish.get(y),Calculations.getCostOfDish(appetizerDish.get(x)),appetizerDish.get(x)});
             x+=2;
             y+=2;
        }
       r=desertDish.size()/2;
        x=0;
        y=1;
       for (int i=0 ; i<r ; i++)
        {
            double c=Calculations.getCostOfDish(desertDish.get(x))* Integer.parseInt(desertDish.get(y));
             m.addRow(new Object[]{Calculations.calculateDesert(desertDish.get(x), Integer.parseInt(desertDish.get(y))),"20%",c,desertDish.get(y),Calculations.getCostOfDish(desertDish.get(x)),desertDish.get(x)});
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

    private ClientGui3arabic() {
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

        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/icons8-rating-64.png"))); // NOI18N
        jButton4.setText("تقييم");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 520, -1, 70));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("الفاتورة");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, 173, 63));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "الحساب بالضريبة المضافة", "نسبة الضريبة", "الحساب بدون الضريبة", "عدد الاطباق", "حساب الطبق الواحد", "الطبق"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 900, -1));

        jButton2.setForeground(new java.awt.Color(0, 153, 255));
        jButton2.setText("mohamedkamaleskander@gmail.com");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 530, -1, 30));

        jButton3.setForeground(new java.awt.Color(0, 153, 255));
        jButton3.setText("amrashraf759@gmail.com ");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 570, -1, 30));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/icons8-back-64.png"))); // NOI18N
        jButton1.setText("خروج");
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
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(87, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(522, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 260, 600));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("للابلاغ  عن أي خطأ , تواصل معنا");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 520, -1, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Login l=null;
        try {
            l = new Login();
        } catch (JAXBException ex) {
            Logger.getLogger(ClientGui3arabic.class.getName()).log(Level.SEVERE, null, ex);
        }
        l.setVisible(true);
        dispose();
         JOptionPane.showMessageDialog(rootPane, "نأمل أن تكون قد استمتعت بخدمتنا");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        Desktop browser = Desktop.getDesktop();
        try {
            browser.browse(new URI("https://mail.google.com/mail/u/0/#search/mohamedkamaleskander%40gmail.com?compose=new"));
            // TODO add your handling code here:
        } catch (URISyntaxException ex) {
            Logger.getLogger(ClientGui3arabic.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientGui3arabic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        Desktop browser = Desktop.getDesktop();
        try {
            browser.browse(new URI("https://mail.google.com/mail/u/1/#search/amrashraf759%40gmail.com?compose=new"));
            // TODO add your handling code here:
        } catch (URISyntaxException ex) {
            Logger.getLogger(ClientGui3arabic.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientGui3arabic.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        RatingGuiarabic r = new RatingGuiarabic(clientname, alltables.get(indix).getNumber());
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
