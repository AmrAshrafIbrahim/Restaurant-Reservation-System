/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Rating.Rating;
import Rating.Ratings;
import UsersByInheritance.Client;
import UsersByInheritance.Cooker;
import UsersByInheritance.Manager;
import UsersByInheritance.User;
import UsersByInheritance.Waiter;
import XmlRead.RestaurantXml;
import XmlRead.TableXml;
import XmlRead.UserXml;
import XmlWrite.Reservations;
import XmlWrite.TableXmlWrite;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import objects.Table;
import objects.TableWriting;

/**
 *
 * @author kamal alexander
 */
public class Managergui extends javax.swing.JFrame {
    List<User> allusers=new ArrayList<>();
    List<Table> alltables=new ArrayList<>();
    List<TableWriting> tablew = new ArrayList<>();
    List<Rating> rlist = new ArrayList<Rating>();
    int numberOfWaiters=0,numberOfCookers=0;
    double salaryOfWaiter=100,salaryOfCooker=150;  //per day
    double mmoney=0;
    
    
    /**
     * Creates new form Manager
     */
    public Managergui() throws JAXBException {
        initComponents();
        readXmlFile();
        sortList();
        printusers();
        display();
        showRating();
        
        
        
           
    }
    
    public void showRating() throws JAXBException
    {
        Ratings r = new Ratings();
        JAXBContext jax=JAXBContext.newInstance(Ratings.class);
         Unmarshaller unm = jax.createUnmarshaller();
          r = (Ratings) unm.unmarshal(new File ("Rating.xml"));
          rlist=r.getRatinglist();
          if(rlist.size()==1)
              ratingText1.setText("No one today wanted to rate");
          else
          {
              double TotalRate=0;
              for(int i=1 ; i<rlist.size() ; i++)
                  TotalRate+=rlist.get(i).getFoodrate()+rlist.get(i).getServicerate()+rlist.get(i).getWaiterrate();
              double show=(TotalRate/(rlist.size()-1))/3;
              ratingText1.setText(Double.toString(show));
          }
          double p=numberOfWaiters*salaryOfWaiter+numberOfCookers*salaryOfCooker;
          if(tablew.size()==1)
              ProfitText.setText("-"+Double.toString(p));
          else
              ProfitText.setText(Double.toString(mmoney-p-0.7*mmoney));
    }
    
    
    public void sortList()
    {
        Collections.sort( tablew ,new Comparator<TableWriting>(){
        
        @Override
        public int compare(TableWriting t1 ,TableWriting t2 ){
            
            if(t1.getNumber()!=t2.getNumber())
             return t1.getNumber()-t2.getNumber();
            
            return t1.getNumber()-t2.getNumber();
       
        }
         
        });
        
        Collections.sort( alltables ,new Comparator<Table>(){
        
        @Override
        public int compare(Table t1 ,Table t2 ){
            
            if(t1.getNumber()!=t2.getNumber())
             return t1.getNumber()-t2.getNumber();
            
            return t1.getSeats()-t2.getSeats();
       
        }
         
        });
        
        
         Collections.sort( rlist ,new Comparator<Rating>(){
        
        @Override
        public int compare(Rating t1 ,Rating t2 ){
            
            if(t1.getTablenumber()!=t2.getTablenumber())
             return t1.getTablenumber()-t2.getTablenumber();
            
            return t1.getServicerate()-t2.getServicerate();
       
        }
         
        });
    }
    

    
    public void readXmlFile() throws JAXBException
    {
       
     JAXBContext jax = JAXBContext.newInstance(Reservations.class);
      Unmarshaller unm= jax.createUnmarshaller();
       Reservations r = (Reservations) unm.unmarshal(new File ("Reservation.xml"));
       
       List<TableXmlWrite> tab = r.getTables().getTableList();
       
        for (TableXmlWrite x : tab) {
            TableWriting te = new TableWriting(x.getNumber(), x.getName(), x.getOrder(), x.getPaid());
            tablew.add(te);
        }
        
        
        
        
        
        JAXBContext jax2 = JAXBContext.newInstance(RestaurantXml.class);
        Unmarshaller unm2= jax2.createUnmarshaller();
         RestaurantXml r2 = (RestaurantXml) unm2.unmarshal(new File ("Restaurant.xml"));
         
         List<TableXml> Tlist = r2.getTables().getTableList();
         List<UserXml> usList = r2.getUsers().getUserList();
      
         
        for (UserXml x :usList) {
                if(x.getRole().equals("Manager"))
            {
                Manager m = new Manager(x.getName(), x.getUsername(), x.getPassword());
                allusers.add(m);
            }
            else if(x.getRole().equals("Client"))
            {
                Client m = new Client(x.getName(), x.getUsername(), x.getPassword());
                allusers.add(m);
            }
              else if(x.getRole().equals("Waiter"))
            {
                Waiter m = new Waiter(x.getName(), x.getUsername(), x.getPassword());
                allusers.add(m);
            }
            
             else if(x.getRole().equals("Cooker"))
            {
                Cooker m = new Cooker(x.getName(), x.getUsername(), x.getPassword());
                allusers.add(m);
            }
            
        }
        
        for (TableXml tableXml : Tlist) {
            Table t = new Table(tableXml.getNumber(),tableXml.getNumberOfSeats(),tableXml.isSmoking());
            alltables.add(t);
        }
    
    }
    void printusers ()
    {
        
        DefaultTableModel m = (DefaultTableModel) jTable1.getModel();
        for (User user : allusers) {
             if (user instanceof Waiter){
            m.addRow(new Object[]{user.getName(),user.getUsername(),"Waiter"});
            numberOfWaiters++;
             }
            else if (user instanceof Cooker){
            m.addRow(new Object[]{user.getName(),user.getUsername(),"Cooker"});
            numberOfCookers++;
            } 
        }
        
         DefaultTableModel x = (DefaultTableModel) jTable4.getModel();
         DefaultTableModel y = (DefaultTableModel) jTable5.getModel();
        
        
        
        for (int i=0  ; i<allusers.size() ; i++ ) {
            if(i<allusers.size() &&  allusers.get(i) instanceof Client){
                 x.addRow(new Object[]{allusers.get(i).getName(),allusers.get(i).getUsername()});
                i++;
            }
             if(i<allusers.size() &&  allusers.get(i) instanceof Client){
                 y.addRow(new Object[]{allusers.get(i).getName(),allusers.get(i).getUsername()});
            }
            
        }
    }
    public void display()
      {
           DefaultTableModel m = (DefaultTableModel) jTable2.getModel();
           int check=0;
           //if(tablew.size()>1){
           for(int j=0;j<alltables.size();j++){
               check=0;
           for (int i=1 ; i<tablew.size() ; i++) {
                  if(alltables.get(j).getNumber()==tablew.get(i).getNumber())
                      check++;    
          }
           if(check>0)
               m.addRow(new Object[]{alltables.get(j).getNumber(),alltables.get(j).getSeats(),alltables.get(j).isSmoke(),"Yes"});
              else 
               m.addRow(new Object[]{alltables.get(j).getNumber(),alltables.get(j).getSeats(),alltables.get(j).isSmoke(),"No"});
       }  
//           else{
//               m.addRow(new Object[]{"There is no reservations uptill now","Empty"}); 
//               // JOptionPane.showMessageDialog(rootPane,"There is no reservations uptill now");
//           }   
                
              for (int i=1 ; i<tablew.size() ; i++) {
                  
                    mmoney+=tablew.get(i).getPaid();
          }
              money.setText(Double.toString(mmoney)+" LE");
      }
//    void printtables()
//    {
//        DefaultTableModel t = (DefaultTableModel) jTable2.getModel();
//        for (Table table : alltables) {
//            t.addRow(new Object[]{table.getNumber(),table.getSeats()," ","not reserved"});
//        }
//        
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jCheckBoxMenuItem2 = new javax.swing.JCheckBoxMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jButton13 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        money = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jButton18 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        ratingText1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        ProfitText = new javax.swing.JLabel();
        jButton19 = new javax.swing.JButton();
        t3 = new javax.swing.JLabel();
        t4 = new javax.swing.JLabel();
        t6 = new javax.swing.JLabel();
        t7 = new javax.swing.JLabel();
        t5 = new javax.swing.JLabel();
        t9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        jMenuItem1.setText("jMenuItem1");

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        jMenu1.setText("jMenu1");

        jMenuItem2.setText("jMenuItem2");

        jMenuItem3.setText("jMenuItem3");

        jCheckBoxMenuItem2.setSelected(true);
        jCheckBoxMenuItem2.setText("jCheckBoxMenuItem2");

        jMenu2.setText("jMenu2");

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        jButton1.setText("jButton1");

        jLabel3.setFont(new java.awt.Font("Arial Black", 3, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 255, 255));
        jLabel3.setText("Tables");

        jButton5.setBackground(new java.awt.Color(172, 217, 217));
        jButton5.setForeground(new java.awt.Color(172, 217, 217));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel2.setText("jLabel2");

        jLabel9.setText("jLabel9");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton13.setBackground(new java.awt.Color(172, 217, 217));
        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/exit.png"))); // NOI18N
        jButton13.setText("Logout");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 110, 70));

        jButton2.setBackground(new java.awt.Color(172, 217, 217));
        jButton2.setForeground(new java.awt.Color(172, 217, 217));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/dinner.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, 90));

        jButton3.setBackground(new java.awt.Color(172, 217, 217));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/money (1).png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 110, 80));

        jButton4.setBackground(new java.awt.Color(172, 217, 217));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/cooking.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 110, 80));

        jButton12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/icons8-chain-start-48.png"))); // NOI18N
        jButton12.setText("Start new day");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, 210, 70));

        jButton14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/icons8-show-property-64.png"))); // NOI18N
        jButton14.setText("Show Reservations");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 210, 80));

        jButton15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/add.png"))); // NOI18N
        jButton15.setText("Add Employee");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 210, 90));

        jButton16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/icons8-remove-100.png"))); // NOI18N
        jButton16.setText("Delete User");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 210, 80));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/angry-caucasian-businessman-shouting-employees_107173-2613.jpg"))); // NOI18N
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 600, -1));

        jTabbedPane1.addTab("Manager", jPanel1);

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Table number", " Number Of Seats", "In Smoking Area", "Reserved"
            }
        ));
        jScrollPane3.setViewportView(jTable2);

        jPanel3.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 610, 137));

        jButton9.setBackground(new java.awt.Color(172, 217, 217));
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/cooking.png"))); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 90, 70));

        jButton10.setBackground(new java.awt.Color(172, 217, 217));
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/money (1).png"))); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 90, 70));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/eating-people-characters-restaurant-staff_82574-8799.jpg"))); // NOI18N
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 610, 180));

        jTabbedPane1.addTab("Tables", jPanel3);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "name", "username", "role"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 626, 143));

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 238, -1, -1));

        jButton6.setBackground(new java.awt.Color(172, 217, 217));
        jButton6.setForeground(new java.awt.Color(172, 217, 217));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/dinner.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 90, 70));

        jButton11.setBackground(new java.awt.Color(172, 217, 217));
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/money (1).png"))); // NOI18N
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 90, 70));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/restaurant-staff-characters-include-chef-assistants-manager-waitress_48866-392.jpg"))); // NOI18N
        jLabel4.setText("jLabel4");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 610, 330));

        jTabbedPane1.addTab("Employee", jPanel2);

        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/clients.jpg"))); // NOI18N
        jLabel14.setText("jLabel14");
        jPanel10.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 610, 200));

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Usename"
            }
        ));
        jScrollPane4.setViewportView(jTable4);

        jPanel10.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 130));

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Usename"
            }
        ));
        jScrollPane5.setViewportView(jTable5);

        jPanel10.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, 320, 130));

        jTabbedPane1.addTab("Users on The System ", jPanel10);

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton7.setBackground(new java.awt.Color(172, 217, 217));
        jButton7.setForeground(new java.awt.Color(172, 217, 217));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/dinner.png"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 110, 110));

        jButton8.setBackground(new java.awt.Color(172, 217, 217));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/cooking.png"))); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, 110, 110));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 51));
        jLabel6.setText("Daily Revenue =");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, 70));

        money.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        money.setForeground(new java.awt.Color(255, 0, 0));
        jPanel4.add(money, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 190, 70));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/money2.jpg"))); // NOI18N
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, -1, 320));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/download.jpg"))); // NOI18N
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -30, 170, 360));

        jTabbedPane1.addTab("daily Revenue", jPanel4);

        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton18.setBackground(new java.awt.Color(255, 0, 0));
        jButton18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton18.setText("Show more details");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton18, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 30, 150, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("The Rating Of Restaurant is : ");
        jPanel9.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 200, 60));

        ratingText1.setForeground(new java.awt.Color(255, 0, 0));
        jPanel9.add(ratingText1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, 210, 40));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("The Profit Of The Restaurant Till Now =");
        jPanel9.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 260, 50));

        ProfitText.setForeground(new java.awt.Color(255, 0, 0));
        jPanel9.add(ProfitText, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 90, 160, 70));

        jButton19.setBackground(new java.awt.Color(255, 0, 0));
        jButton19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton19.setText("Show more details");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton19, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 110, 150, 30));

        t3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jPanel9.add(t3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 170, 30));

        t4.setForeground(new java.awt.Color(255, 0, 0));
        jPanel9.add(t4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 250, 100, 30));

        t6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        t6.setForeground(new java.awt.Color(0, 0, 204));
        jPanel9.add(t6, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 280, 420, 30));

        t7.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jPanel9.add(t7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 480, 40));
        jPanel9.add(t5, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 234, -1, 40));

        t9.setForeground(new java.awt.Color(255, 0, 0));
        jPanel9.add(t9, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 250, 50, 30));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jPanel9.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, 360, 40));

        jTabbedPane1.addTab("Statistics", jPanel9);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 620, 350));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed

        jTabbedPane1.setSelectedIndex(2);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

        jTabbedPane1.setSelectedIndex(1);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed

        jTabbedPane1.setSelectedIndex(3);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        jTabbedPane1.setSelectedIndex(1);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed

        jTabbedPane1.setSelectedIndex(3);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed

        jTabbedPane1.setSelectedIndex(2);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed

        
        // TODO add your handling code here:
        ManagerReservation r = new ManagerReservation(tablew);
        r.setVisible(true);
       
        
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        StartNewDay s =new StartNewDay();
        s.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        jTabbedPane1.setSelectedIndex(2);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        jTabbedPane1.setSelectedIndex(4);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        jTabbedPane1.setSelectedIndex(1);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed

        Login l=null;
        try {
            l = new Login();
        } catch (JAXBException ex) {
            Logger.getLogger(ClientGui3.class.getName()).log(Level.SEVERE, null, ex);
        }
        l.setVisible(true);
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed

        Newemployee l = new Newemployee(allusers,alltables);
        l.setVisible(true);
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
        DeleteUser d =new DeleteUser();
        d.setVisible(true);
        dispose();
        
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
        if(rlist.size()==1)
            JOptionPane.showMessageDialog(rootPane, "There is no data to show");
        
        
        else
        {
            MRating2 m = new MRating2(rlist);
            m.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
        double BEP=(numberOfWaiters*salaryOfWaiter+numberOfCookers*salaryOfCooker)/0.3;   //we supposed that there is fixed costs ( Salaries of employees) 
                                                                                         // and   Variable Costs (which is 70% Of the revenues )
        jLabel13.setText("salary Of Waiter = 100   salary Of Cooker = 150");
        t3.setText(" The  break-even point =");
        t4.setText(Double.toString(BEP));
        t9.setText("LE");
        t6.setText("(Which is the stage at which revenues equal costs)");
        t7.setText("So You will start earning after the  break-even point ");
    }//GEN-LAST:event_jButton19ActionPerformed

    /**
     * @param args the command line arguments
     */
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ProfitText;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JLabel money;
    private javax.swing.JLabel ratingText1;
    private javax.swing.JLabel t3;
    private javax.swing.JLabel t4;
    private javax.swing.JLabel t5;
    private javax.swing.JLabel t6;
    private javax.swing.JLabel t7;
    private javax.swing.JLabel t9;
    // End of variables declaration//GEN-END:variables
}
