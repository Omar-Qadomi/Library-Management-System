import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class JavaBooks extends JFrame implements ActionListener {
  JButton connectButton, exitButton, insertButton, deleteButton, deleteAllButton, searchButton, updateButton,
  clearButton, printButton, hideButton, okButton;
  JTextField ISBN, title, author, publication_year;
  JPanel panel, panelB,panelU,panelD,panelA,panelP,panelS;
  JComboBox genre;
  JRadioButton b1, b2, b3, b4;
  ButtonGroup bg;

  JTable tb;
  static String col[] = {
    "ID",
    "ISBN",
    "Title",
    "Author",
    "Genre",
    "P-year"

  };
  String row[][] = new String[100][6];
  JScrollPane sp;

  static String database_name = "lib"; //change this
  static String database_table = "books"; //change this
  // Change info according to the user;
  private static String url = "jdbc:mysql://localhost:3306/" + database_name;
  private static String username = ""; //change this
  private static String password = ""; //change this
  private static String path = "./src/"; //change this
  JFrame fa;
  Connection connection;
  Statement statement;

  JLabel companyLogo, ISBNL, titleL, authorL, publication_yearL, genreL, infoL, errorL, ifo, infoLL, banner;

  public JavaBooks() {
    super("Library");
    setSize(1915, 1080);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(null);

    Font f = new Font("monospaced", Font.BOLD, 26);
    Font g = new Font("monospaced", Font.BOLD, 20);
    Color back = new Color(17, 17, 31);
    Color fore = new Color(223, 106, 176);
    panel = new JPanel();
    panel.setBounds(900, 100, 900, 750);
    panel.setBackground(fore);
    panel.setLayout(null);
    panel.setBorder(BorderFactory.createLineBorder(new Color(124, 62, 108), 7));

    panelU = new JPanel();
    panelU.setBounds(900, 100, 900, 750);
    panelU.setBackground(fore);
    panelU.setLayout(null);
    panelU.setBorder(BorderFactory.createLineBorder(new Color(124, 62, 108), 7));

    panelA = new JPanel();
    panelA.setBounds(900, 100, 900, 750);
    panelA.setBackground(fore);
    panelA.setLayout(null);
    panelA.setBorder(BorderFactory.createLineBorder(new Color(124, 62, 108), 7));

    panelD = new JPanel();
    panelD.setBounds(900, 100, 900, 750);
    panelD.setBackground(fore);
    panelD.setLayout(null);
    panelD.setBorder(BorderFactory.createLineBorder(new Color(124, 62, 108), 7));

    panelS = new JPanel();
    panelS.setBounds(900, 100, 900, 750);
    panelS.setBackground(fore);
    panelS.setLayout(null);
    panelS.setBorder(BorderFactory.createLineBorder(new Color(124, 62, 108), 7));

    panelP = new JPanel();
    panelP.setBounds(900, 100, 900, 750);
    panelP.setBackground(fore);
    panelP.setLayout(null);
    panelP.setBorder(BorderFactory.createLineBorder(new Color(124, 62, 108), 7));
    

    panelB = new JPanel();
    panelB.setBackground(fore);
    panelB.setBounds(100, 340, 400, 450);
    panelB.setBorder(BorderFactory.createLineBorder(new Color(124, 62, 108), 7));

    getContentPane().setBackground(back);

    bg = new ButtonGroup();
    b1 = new JRadioButton("By Title");
    b1.setFont(g);
    b1.setForeground(fore);
    b1.setBackground(back);
    b1.setBounds(1000, 200, 300, 40);

    b2 = new JRadioButton("By Author");
    b2.setFont(g);
    b2.setForeground(fore);
    b2.setBackground(back);
    b2.setBounds(1000, 300, 300, 40);

    b3 = new JRadioButton("By Genre");
    b3.setFont(g);
    b3.setForeground(fore);
    b3.setBackground(back);
    b3.setBounds(1400, 200, 300, 40);
    b4 = new JRadioButton("By ISBN");
    b4.setFont(g);
    b4.setForeground(fore);
    b4.setBackground(back);
    b4.setBounds(1400, 300, 300, 40);

    bg.add(b1);
    bg.add(b2);
    bg.add(b3);
    bg.add(b4);
    companyLogo = new JLabel(new ImageIcon(path + "logom.png"));
    companyLogo.setBounds(95, 20, 400, 300);
    connectButton = new JButton("Connect To DataBase");
    connectButton.setForeground(fore);
    connectButton.setBackground(back);
    connectButton.setBounds(150, 400, 300, 40);
    connectButton.setFont(g);

    insertButton = new JButton("Add");
    insertButton.setForeground(fore);
    insertButton.setBackground(back);
    insertButton.setFont(g);
    insertButton.setBounds(150, 500, 300, 40);

    updateButton = new JButton("Update");
    updateButton.setForeground(fore);
    updateButton.setBackground(back);
    updateButton.setBounds(150, 550, 300, 40);
    updateButton.setFont(g);

    deleteButton = new JButton("Delete");
    deleteButton.setForeground(fore);
    deleteButton.setBackground(back);
    deleteButton.setBounds(150, 600, 300, 40);
    deleteButton.setFont(g);

    printButton = new JButton("Show");
    printButton.setForeground(fore);
    printButton.setBackground(back);
    printButton.setBounds(150, 450, 300, 40);
    printButton.setFont(g);

    exitButton = new JButton("Exit");
    exitButton.setForeground(fore);
    exitButton.setBackground(back);
    exitButton.setBounds(150, 700, 300, 40);
    exitButton.setFont(g);

    searchButton = new JButton("Serach");
    searchButton.setForeground(fore);
    searchButton.setBackground(back);
    searchButton.setFont(g);
    searchButton.setBounds(150, 650, 300, 40);

    clearButton = new JButton("Clear");
    clearButton.setForeground(fore);
    clearButton.setBackground(back);

    clearButton.setFont(g);
    clearButton.setBounds(600, 260, 200, 40);

    hideButton = new JButton("Hide");
    hideButton.setForeground(fore);
    hideButton.setBackground(back);
    hideButton.setFont(g);
    hideButton.setBounds(375, 260, 200, 40);
    hideButton.setVisible(false);

    okButton = new JButton("Confirm");
    okButton.setForeground(fore);
    okButton.setBackground(back);
    okButton.setFont(g);
    okButton.setBounds(600, 600, 200, 40);

    errorL = new JLabel("Error: Record not found");
    errorL.setFont(g);
    errorL.setBackground(Color.red);
    errorL.setForeground(Color.WHITE);
    errorL.setOpaque(true);
    errorL.setVisible(false);
    errorL.setBounds(950, 600, 600, 40);
    errorL.setVisible(false);

    ifo = new JLabel(" Enter the ISBN of book that you want to delete");
    ifo.setFont(g);
    ifo.setBackground(back);
    ifo.setForeground(fore);
    ifo.setOpaque(true);
    ifo.setVisible(false);
    ifo.setBounds(950, 600, 600, 40);
    ifo.setVisible(false);

    ISBN = new JTextField();
    ISBN.setBackground(back);
    ISBN.setForeground(fore);
    ISBN.setCaretColor(fore);

    ISBN.setFont(g);
    ISBNL = new JLabel("ISBN");
    ISBNL.setFont(f);
    title = new JTextField();
    title.setBackground(back);
    title.setForeground(fore);
    title.setCaretColor(fore);
    title.setFont(g);
    titleL = new JLabel("Title");
    titleL.setFont(f);
    author = new JTextField();
    author.setBackground(back);
    author.setForeground(fore);
    author.setCaretColor(fore);

    author.setFont(g);
    authorL = new JLabel("Author");
    authorL.setFont(f);
    publication_year = new JTextField();
    publication_year.setBackground(back);
    publication_year.setForeground(fore);
    publication_year.setCaretColor(fore);
    publication_year.setFont(g);
    publication_yearL = new JLabel("P-Year");
    publication_yearL.setFont(f);
    genreL = new JLabel("Genre");
    genreL.setFont(f);
    String[] status = {
      "Art",
      "Business",
      "History",
      "Hororr",
      "Mystery",
      "Religion",
      "Thriller"
    };
    genre = new JComboBox < > (status);
    genre.setSelectedIndex(0);

    genre.setBounds(140, 260, 200, 40);
    genre.setBackground(back);
    genre.setForeground(fore);
    genre.setFont(f);

    infoL = new JLabel("");
    infoL.setFont(f);
    infoL.setBounds(40, 20, 300, 40);

    infoLL = new JLabel("Choose Option");
    infoLL.setFont(f);
    infoLL.setBounds(200, 400, 300, 40);
    infoLL.setForeground(back);

    banner = new JLabel("  Connected to DataBase");
    banner.setBounds(240, 400, 400, 40);
    banner.setForeground(fore);
    banner.setBackground(back);
    banner.setOpaque(true);
    banner.setFont(f);

    ISBN.setBounds(140, 100, 200, 40);
    ISBNL.setBounds(40, 100, 100, 40);
    title.setBounds(600, 100, 200, 40);
    titleL.setBounds(500, 100, 100, 40);
    publication_year.setBounds(600, 180, 200, 40);
    publication_yearL.setBounds(490, 180, 200, 40);
    author.setBounds(140, 180, 200, 40);
    authorL.setBounds(30, 180, 100, 40);
    genreL.setBounds(30, 260, 100, 40);

    connectButton.addActionListener(this);
    exitButton.addActionListener(this);
    insertButton.addActionListener(this);
    deleteButton.addActionListener(this);
    searchButton.addActionListener(this);
    updateButton.addActionListener(this);
    clearButton.addActionListener(this);
    printButton.addActionListener(this);
    hideButton.addActionListener(this);
    okButton.addActionListener(this);

    panel.setVisible(false);
    panel.add(okButton);
    panel.add(ISBN);
    panel.add(ISBNL);
    panel.add(publication_yearL);
    panel.add(publication_year);
    panel.add(title);
    panel.add(titleL);
    panel.add(author);
    panel.add(authorL);
    panel.add(genreL);
    panel.add(infoL);
    panel.add(genre);
    panel.add(banner);

    getContentPane().add(insertButton);
    getContentPane().add(deleteButton);

    getContentPane().add(searchButton);
    getContentPane().add(updateButton);
    panel.add(clearButton);
    panel.add(hideButton);

    getContentPane().add(printButton);
    getContentPane().add(companyLogo);
    getContentPane().add(connectButton);
    getContentPane().add(errorL);
    getContentPane().add(ifo);
    getContentPane().add(panel);
    /* 
    getContentPane().add(panelU);
    getContentPane().add(panelP);
    getContentPane().add(panelD);
    getContentPane().add(panelS);
    getContentPane().add(panelA);
    */
    getContentPane().add(exitButton);
    getContentPane().add(b1);
    getContentPane().add(b2);
    getContentPane().add(b3);
    getContentPane().add(b4);
    getContentPane().add(infoLL);

    tb = new JTable(row, col);

    tb.setFont(new Font("monospaced", Font.BOLD, 20));
    tb.setForeground(fore);
    tb.setBackground(back);
    tb.setEnabled(false);
    tb.getColumnModel().getColumn(2).setPreferredWidth(100);
    tb.setRowHeight(30);

    tb.getTableHeader().setFont(new Font("monospaced", Font.BOLD, 20));
    tb.getTableHeader().setBackground(new Color(15, 48, 84));
    tb.getTableHeader().setForeground(fore);
    sp = new JScrollPane(tb);

    sp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
    sp.setBounds(950, 450, 800, 350);
    sp.setBackground(back);
    sp.setForeground(fore);
    sp.setVisible(false);
    tb.setVisible(false);

    getContentPane().add(sp);
    getContentPane().add(panel);
    getContentPane().add(panelB);

    b1.setVisible(false);
    b2.setVisible(false);
    b3.setVisible(false);
    b4.setVisible(false);
    ISBN.setVisible(false);
    ISBNL.setVisible(false);
    title.setVisible(false);
    titleL.setVisible(false);
    author.setVisible(false);
    authorL.setVisible(false);
    genre.setVisible(false);
    genreL.setVisible(false);
    publication_year.setVisible(false);
    publication_yearL.setVisible(false);
    clearButton.setVisible(false);
    okButton.setVisible(false);
    infoL.setVisible(false);

    setVisible(true);
  }

  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == connectButton) {
      panel.setVisible(true);
      try {
        connection = DriverManager.getConnection(url, username, password);
        statement = connection.createStatement();
        panel.setVisible(true);
        connectButton.setVisible(false);

      } catch (SQLException E) {
        JOptionPane.showMessageDialog(this, "Error: \n" + E);
      }

    }
    if (e.getSource() == insertButton && !(publication_yearL.isVisible()) || e.getSource() == insertButton && infoL.getText().equals("Update Panel")) {
      if (panel.isVisible()) {
        sp.setVisible(false);
        tb.setVisible(false);
        hideButton.setVisible(false);
        ifo.setVisible(false);
        okButton.setVisible(false);
        b1.setVisible(false);
        b2.setVisible(false);
        b3.setVisible(false);
        b4.setVisible(false);
        ISBN.setVisible(true);
        ISBNL.setVisible(true);
        title.setVisible(true);
        titleL.setVisible(true);
        author.setVisible(true);
        authorL.setVisible(true);
        genre.setVisible(true);
        genreL.setVisible(true);
        publication_year.setVisible(true);
        publication_yearL.setVisible(true);
        clearButton.setVisible(true);
        infoL.setText("Add Panel");
        infoL.setVisible(true);
        banner.setVisible(false);
        ISBN.setText("");
          author.setText("");
          title.setText("");
          publication_year.setText("");

      }

    }
    if (e.getSource() == insertButton && publication_yearL.isVisible()) {
      if (!(ISBN.getText().equals(""))) {
        ifo.setVisible(false);
        try {
          sp.setVisible(false);
        tb.setVisible(false);
        hideButton.setVisible(false);
          int isid = Integer.parseInt(ISBN.getText());
          String sql = "INSERT INTO " + database_table + "(ISBN, Title,Publication_year,Author,Genre) VALUES (" + isid + "," + "'" + title.getText() + "'" + "," + "'" + publication_year.getText() + "'" + "," + "'" + author.getText() + "'" + "," + "'" + (String) genre.getSelectedItem() + "'" + ")";
          statement.executeUpdate(sql);
          JOptionPane.showMessageDialog(this, "insertion done ");
          publication_year.setText("");
          ISBN.setText("");
          author.setText("");
          title.setText("");
          ifo.setVisible(false);
          banner.setVisible(true);
          ISBN.setVisible(false);
          ISBNL.setVisible(false);
          errorL.setVisible(false);
          errorL.setText("Error: Record not found");
          b1.setVisible(false);
          b2.setVisible(false);
          b3.setVisible(false);
          b4.setVisible(false);
          ISBN.setVisible(false);
          ISBNL.setVisible(false);
          title.setVisible(false);
          titleL.setVisible(false);
          author.setVisible(false);
          authorL.setVisible(false);
          genre.setVisible(false);
          genreL.setVisible(false);
          publication_year.setVisible(false);
          publication_yearL.setVisible(false);
          clearButton.setVisible(false);
          okButton.setVisible(false);
          infoL.setVisible(false);
          sp.setVisible(false);
          tb.setVisible(false);
          hideButton.setVisible(false);
        } catch (Exception e2) {
          JOptionPane.showMessageDialog(this, "Sorry Record not added \n" + e2);
        }
      }
    }

    if (e.getSource() == searchButton && !(publication_yearL.isVisible()) || e.getSource() == searchButton && infoL.getText().equals("Add Panel")) {
      if (panel.isVisible()) {
        infoL.setText("Search Panel");
        sp.setVisible(false);
        tb.setVisible(false);
        hideButton.setVisible(false);
        infoL.setVisible(true);
        ifo.setVisible(false);
        clearButton.setVisible(false);
        okButton.setVisible(true);
        ISBN.setVisible(false);
        ISBNL.setVisible(false);
        title.setVisible(false);
        titleL.setVisible(false);
        author.setVisible(false);
        authorL.setVisible(false);
        genre.setVisible(false);
        genreL.setVisible(false);
        publication_year.setVisible(false);
        publication_yearL.setVisible(false);
        banner.setVisible(false);
        System.out.println(hideButton.isVisible());

        b1.setVisible(true);
        b2.setVisible(true);
        b3.setVisible(true);
        b4.setVisible(true);

      }
    }

    if (e.getSource() == okButton && b1.isSelected()) {
      ISBN.setVisible(false);
      ISBNL.setVisible(false);
      title.setVisible(true);
      titleL.setVisible(true);
      author.setVisible(false);
      authorL.setVisible(false);
      genre.setVisible(false);
      genreL.setVisible(false);
      publication_year.setVisible(false);

      publication_yearL.setVisible(false);
      okButton.setVisible(false);

      b1.setVisible(false);
      b2.setVisible(false);
      b3.setVisible(false);
      b4.setVisible(false);

    }
    if (e.getSource() == okButton && b2.isSelected()) {
      ISBN.setVisible(false);
      ISBNL.setVisible(false);
      title.setVisible(false);
      titleL.setVisible(false);
      author.setVisible(true);
      authorL.setVisible(true);
      genre.setVisible(false);
      genreL.setVisible(false);
      publication_year.setVisible(false);

      publication_yearL.setVisible(false);

      okButton.setVisible(false);
      b1.setVisible(false);
      b2.setVisible(false);
      b3.setVisible(false);
      b4.setVisible(false);

    }
    if (e.getSource() == okButton && b3.isSelected()) {
      ISBN.setVisible(false);
      ISBNL.setVisible(false);
      title.setVisible(false);
      titleL.setVisible(false);
      author.setVisible(false);
      authorL.setVisible(false);
      genre.setVisible(true);
      genreL.setVisible(true);
      publication_year.setVisible(false);
      publication_year.setEnabled(false);
      publication_yearL.setVisible(false);
      okButton.setVisible(false);

      b1.setVisible(false);
      b2.setVisible(false);
      b3.setVisible(false);
      b4.setVisible(false);

    }
    if (e.getSource() == okButton && b4.isSelected()) {
      ISBN.setVisible(true);
      ISBNL.setVisible(true);
      title.setVisible(false);
      titleL.setVisible(false);
      author.setVisible(false);
      authorL.setVisible(false);
      genre.setVisible(false);
      genreL.setVisible(false);
      okButton.setVisible(false);
      publication_year.setVisible(false);
      publication_yearL.setVisible(false);

      b1.setVisible(false);
      b2.setVisible(false);
      b3.setVisible(false);
      b4.setVisible(false);

    }

    if (e.getSource() == searchButton && b1.isSelected()) {

      ifo.setVisible(false);
      banner.setVisible(false);
      sp.setVisible(false);
      tb.setVisible(false);

        hideButton.setVisible(false);
      for (int i = 0; i < 100; i++) {
        for (int j = 0; j < 6; j++) {
          row[i][j] = null;
        }
      }
      try {
        String sql = "SELECT * FROM " + database_table + " WHERE Title=" + "'" + title.getText() + "'";

        ResultSet rs = null;
        rs = statement.executeQuery(sql);
        int i = 0;

        while (rs.next()) {
          int j = 0;
          String id = String.valueOf(rs.getInt("ID"));
          String name = String.valueOf(rs.getString("ISBN"));
          String m = rs.getString("Title");
          String ma = rs.getString("Author");
          String st = rs.getString("Genre");
          String pr = rs.getString("Publication_year");
          String f[][] = {
            {
              id,
              name,
              m,
              ma,
              st,
              pr

            }
          };
          while (j != 6) {
            row[i][j] = f[0][j];
            j++;

          }
          i++;

        }
        if (row[0][0] != null) {
          errorL.setVisible(false);
          sp.setVisible(true);
          tb.setVisible(true);
          hideButton.setVisible(true);

        } else {
          if (!(title.getText().equals(""))) {
            errorL.setVisible(true);
          }
        }
        bg.clearSelection();

      } catch (Exception e2) {
        JOptionPane.showMessageDialog(this, "Sorry Record is not found \n" + e2);
      }
    }
    if (e.getSource() == searchButton && b2.isSelected()) {
      ifo.setVisible(false);
      banner.setVisible(false);
      sp.setVisible(false);
      tb.setVisible(false);
      
        hideButton.setVisible(false);
      for (int i = 0; i < 100; i++) {
        for (int j = 0; j < 6; j++) {
          row[i][j] = null;
        }
      }
      try {

        String sql = "SELECT * FROM " + database_table + " WHERE Author=" + "'" + author.getText() + "'";

        ResultSet rs = null;
        rs = statement.executeQuery(sql);
        int i = 0;

        while (rs.next()) {
          int j = 0;
          String id = String.valueOf(rs.getInt("ID"));
          String name = String.valueOf(rs.getString("ISBN"));
          String m = rs.getString("Title");
          String ma = rs.getString("Author");
          String st = rs.getString("Genre");
          String pr = rs.getString("Publication_year");
          String f[][] = {
            {
              id,
              name,
              m,
              ma,
              st,
              pr
            }
          };
          while (j != 6) {
            row[i][j] = f[0][j];
            j++;

          }
          i++;

        }
        if (row[0][0] != null) {
          errorL.setVisible(false);
          sp.setVisible(true);
          tb.setVisible(true);
          hideButton.setVisible(true);

        } else {
          if (!(author.getText().equals(""))) {
            errorL.setVisible(true);
          }
        }
        bg.clearSelection();

      } catch (Exception e2) {
        JOptionPane.showMessageDialog(this, "Sorry Record is not found \n" + e2);
      }
    }

    if (e.getSource() == searchButton && b3.isSelected()) {
      ifo.setVisible(false);
      banner.setVisible(false);
      sp.setVisible(false);
      tb.setVisible(false);
        hideButton.setVisible(false);
      for (int i = 0; i < 100; i++) {
        for (int j = 0; j < 6; j++) {
          row[i][j] = null;
        }
      }
      try {
        String sql = "SELECT * FROM " + database_table + " WHERE Genre=" + "'" + (String) genre.getSelectedItem() + "'";
        ResultSet rs = null;
        rs = statement.executeQuery(sql);
        int i = 0;

        while (rs.next()) {
          int j = 0;
          String id = String.valueOf(rs.getInt("ID"));
          String name = String.valueOf(rs.getString("ISBN"));
          String m = rs.getString("Title");
          String ma = rs.getString("Author");
          String st = rs.getString("Genre");
          String pr = rs.getString("Publication_year");
          String f[][] = {
            {
              id,
              name,
              m,
              ma,
              st,
              pr

            }
          };
          while (j != 6) {
            row[i][j] = f[0][j];
            j++;

          }
          i++;

        }

        if (row[0][0] != null) {
          errorL.setVisible(false);
          sp.setVisible(true);
          tb.setVisible(true);
          hideButton.setVisible(true);

        } else {
          if (!(ISBN.getText().equals(""))) {
            errorL.setVisible(true);
          }

        }
        /* 
         errorL.setVisible(false);
         sp.setVisible(true);
         tb.setVisible(true);
         hideButton.setVisible(true);
          */

        bg.clearSelection();

      } catch (Exception e2) {
        JOptionPane.showMessageDialog(this, "Sorry Record is not found \n" + e2);
      }
    }
    if (e.getSource() == searchButton && b4.isSelected()) {

      ifo.setVisible(false);
      banner.setVisible(false);
      sp.setVisible(false);
      tb.setVisible(false);
      
        hideButton.setVisible(false);
      for (int i = 0; i < 100; i++) {
        for (int j = 0; j < 6; j++) {
          row[i][j] = null;
        }
      }
      try {
        int isid = Integer.parseInt(ISBN.getText());
        String sql = "SELECT * FROM " + database_table + " WHERE ISBN=" + isid;

        ResultSet rs = null;
        rs = statement.executeQuery(sql);
        int i = 0;

        while (rs.next()) {
          int j = 0;
          String id = String.valueOf(rs.getInt("ID"));
          String name = String.valueOf(rs.getString("ISBN"));
          String m = rs.getString("Title");
          String ma = rs.getString("Author");
          String st = rs.getString("Genre");
          String pr = rs.getString("Publication_year");
          String f[][] = {
            {
              id,
              name,
              m,
              ma,
              st,
              pr

            }
          };
          /*  while (j != 6) {
            row[i][j] = f[0][j];
            j++;

          }
          break;*/

          while (j != 6) {
            row[i][j] = f[0][j];
            j++;

          }
          i++;

        }
        if (row[0][0] != null) {
          errorL.setVisible(false);
          sp.setVisible(true);
          tb.setVisible(true);
          hideButton.setVisible(true);

        } else {
          if (!(ISBN.getText().equals(""))) {
            errorL.setVisible(true);
          }

        }
        bg.clearSelection();

      } catch (Exception e2) {
        JOptionPane.showMessageDialog(this, "Sorry Record is not found \n" + e2);
      }
    }
    if (e.getSource() == printButton) {
      b1.setVisible(false);
      b2.setVisible(false);
      b3.setVisible(false);
      b4.setVisible(false);
      sp.setVisible(false);
      tb.setVisible(false);
      ifo.setVisible(false);
      infoL.setText("");
      banner.setVisible(false);
      okButton.setVisible(false);

      for (int i = 0; i < 100; i++) {
        for (int j = 0; j < 6; j++) {
          row[i][j] = null;
        }
      }

      try {

        String sql = "SELECT * FROM " + database_table;
        ResultSet rs = null;
        rs = statement.executeQuery(sql);

        int i = 0;

        while (rs.next()) {
          int j = 0;
          String id = String.valueOf(rs.getInt("ID"));
          String name = String.valueOf(rs.getString("ISBN"));
          String m = rs.getString("Title");
          String ma = rs.getString("Author");
          String st = rs.getString("Genre");
          String pr = rs.getString("Publication_year");
          String f[][] = {
            {
              id,
              name,
              m,
              ma,
              st,
              pr

            }
          };
          while (j != 6) {
            row[i][j] = f[0][j];
            j++;

          }
          i++;
        }
        errorL.setVisible(false);
        sp.setVisible(true);
        tb.setVisible(true);
        b1.setVisible(false);
        b2.setVisible(false);
        b3.setVisible(false);
        b4.setVisible(false);
        ISBN.setVisible(false);
        ISBNL.setVisible(false);
        title.setVisible(false);
        titleL.setVisible(false);
        author.setVisible(false);
        authorL.setVisible(false);
        genre.setVisible(false);
        genreL.setVisible(false);
        publication_year.setVisible(false);
        publication_yearL.setVisible(false);
        clearButton.setVisible(false);
        okButton.setVisible(false);
        infoL.setVisible(false);
        banner.setVisible(true);

        hideButton.setVisible(true);

      } catch (Exception e2) {
        JOptionPane.showMessageDialog(this, "Connect to DataBase\n");
      }
    }

    
    if (e.getSource() == okButton && b1.isSelected() && infoL.getText().equals("Update Panel")) {
      if (panel.isVisible()) {
        ifo.setVisible(false);
        okButton.setVisible(false);
        b1.setVisible(false);
        b2.setVisible(false);
        b3.setVisible(false);
        b4.setVisible(false);
        ISBN.setVisible(true);
        ISBNL.setVisible(true);
        title.setVisible(true);
        titleL.setVisible(true);
        author.setVisible(true);
        authorL.setVisible(true);
        genre.setVisible(true);
        genreL.setVisible(true);
        publication_year.setVisible(true);
        publication_yearL.setVisible(true);
        clearButton.setVisible(true);
        infoL.setText("Update Panel");
        infoL.setVisible(true);
        banner.setVisible(false);
        
        

      }

    }
    if (e.getSource() == okButton && b4.isSelected() && infoL.getText().equals("Update Panel")) {
      if (panel.isVisible()) {
        ifo.setVisible(false);
        okButton.setVisible(false);
        b1.setVisible(false);
        b2.setVisible(false);
        b3.setVisible(false);
        b4.setVisible(false);
        ISBN.setVisible(true);
        ISBNL.setVisible(true);
        title.setVisible(true);
        titleL.setVisible(true);
        author.setVisible(true);
        authorL.setVisible(true);
        genre.setVisible(true);
        genreL.setVisible(true);
        publication_year.setVisible(true);
        publication_yearL.setVisible(true);
        clearButton.setVisible(true);
        infoL.setText("Update Panel");
        infoL.setVisible(true);
        banner.setVisible(false);
        

      }

    }
    if (e.getSource() == updateButton && b4.isSelected() && infoL.getText().equals("Update Panel")) {
      ifo.setVisible(false);
      if (panel.isVisible()) {
        banner.setVisible(false);

        try {
          bg.clearSelection();
          int isid = Integer.parseInt(ISBN.getText());

          String sql = "UPDATE " + database_table + " SET Title=" + "'" + title.getText() + "'" + "," + "Author=" + "'" + author.getText() + "'" + "," + "Genre=" + "'" + (String) genre.getSelectedItem() + "'" + "," + "Publication_year=" + "'" + publication_year.getText() + "'" + " WHERE ISBN=" + isid;
          statement.executeUpdate(sql);
          JOptionPane.showMessageDialog(this, "Record updated ");
          ISBN.setText("");
          author.setText("");
          title.setText("");
          publication_year.setText("");
          ifo.setVisible(false);
          banner.setVisible(true);
          ISBN.setVisible(false);
          ISBNL.setVisible(false);
          errorL.setVisible(false);
          errorL.setText("Error: Record not found");
          b1.setVisible(false);
          b2.setVisible(false);
          b3.setVisible(false);
          b4.setVisible(false);
          ISBN.setVisible(false);
          ISBNL.setVisible(false);
          title.setVisible(false);
          titleL.setVisible(false);
          author.setVisible(false);
          authorL.setVisible(false);
          genre.setVisible(false);
          genreL.setVisible(false);
          publication_year.setVisible(false);
          publication_yearL.setVisible(false);
          clearButton.setVisible(false);
          okButton.setVisible(false);
          sp.setVisible(false);
        tb.setVisible(false);
        hideButton.setVisible(false);
          infoL.setVisible(false);
        } catch (Exception e2) {
          JOptionPane.showMessageDialog(this, "Error: Record is not updated \n" + "Record Not Found [ISBN]");
        }
      }
    }
    else if (e.getSource() == updateButton && b1.isSelected() && infoL.getText().equals("Update Panel")) {
      ifo.setVisible(false);
      
      if (panel.isVisible()) {
        banner.setVisible(false);

        try {
          System.out.println("update");
          bg.clearSelection();
          System.out.println("up" + title.getText());
          int isid = Integer.parseInt(ISBN.getText());
          String sql = "UPDATE " + database_table + " SET ISBN=" + isid + "," + "Author=" + "'" + author.getText() + "'" + "," + "Genre=" + "'" + (String) genre.getSelectedItem() + "'" + "," + "Publication_year=" + "'" + publication_year.getText() + "'" + " WHERE Title=" + "'" + title.getText() + "'";
          statement.executeUpdate(sql);
          JOptionPane.showMessageDialog(this, "Record updated ");
          ifo.setVisible(false);
          banner.setVisible(true);
          ISBN.setVisible(false);
          ISBNL.setVisible(false);
          errorL.setVisible(false);
          errorL.setText("Error: Record not found");
          b1.setVisible(false);
          b2.setVisible(false);
          b3.setVisible(false);
          b4.setVisible(false);
          ISBN.setVisible(false);
          ISBNL.setVisible(false);
          title.setVisible(false);
          titleL.setVisible(false);
          author.setVisible(false);
          authorL.setVisible(false);
          genre.setVisible(false);
          genreL.setVisible(false);
          publication_year.setVisible(false);
          publication_yearL.setVisible(false);
          clearButton.setVisible(false);
          okButton.setVisible(false);
          sp.setVisible(false);
        tb.setVisible(false);
        hideButton.setVisible(false);
          infoL.setVisible(false);

        } catch (Exception e2) {
          JOptionPane.showMessageDialog(this, "Error: Record is not updated \n" + "Record Not Found [Title]");
        }
      }
    }
    else if (e.getSource() == updateButton) {
      if (panel.isVisible()) {
        infoL.setText("Update Panel");
        infoL.setVisible(true);
        b1.setVisible(true);
        b2.setVisible(false);
        b3.setVisible(false);
        b4.setVisible(true);
        okButton.setVisible(true);
        banner.setVisible(false);
        ifo.setVisible(false);
        ISBN.setVisible(false);
        ISBNL.setVisible(false);
        errorL.setVisible(false);
        errorL.setText("Error: Record not found");
        ISBN.setVisible(false);
        ISBNL.setVisible(false);
        title.setVisible(false);
        titleL.setVisible(false);
        author.setVisible(false);
        authorL.setVisible(false);
        genre.setVisible(false);
        genreL.setVisible(false);
        publication_year.setVisible(false);
        publication_yearL.setVisible(false);
        clearButton.setVisible(false);
        sp.setVisible(false);
        tb.setVisible(false);
        hideButton.setVisible(false);
       
      }

    }
    if(e.getSource() == deleteButton){
      if (panel.isVisible()) {
        ISBN.setText("");
        b1.setVisible(false);
        b2.setVisible(false);
        b3.setVisible(false);
        okButton.setVisible(false);
        b4.setVisible(false);
        ISBN.setVisible(false);
        ISBNL.setVisible(false);
        title.setVisible(false);
        titleL.setVisible(false);
        author.setVisible(false);
        authorL.setVisible(false);
        genre.setVisible(false);
        genreL.setVisible(false);
        publication_year.setVisible(false);
        publication_yearL.setVisible(false);
        clearButton.setVisible(false);
        sp.setVisible(false);
        tb.setVisible(false);
        hideButton.setVisible(false);

        clearButton.setVisible(true);
        banner.setVisible(false);
        if (ifo.isEnabled()) {
          ifo.setVisible(true);
          ifo.setEnabled(false);
        } else if (!(ifo.isEnabled())) {
          ifo.setVisible(true);
          ifo.setEnabled(true);

        }
        ISBN.setVisible(true);
        ISBNL.setVisible(true);
        infoL.setText("Delete Panel");
        infoL.setVisible(true);
        banner.setVisible(false);

        
      }

    }

    //if (e.getSource() == deleteButton && infoL.getText().equals("Add Panel") || e.getSource() == deleteButton && infoL.getText().equals("Update Panel")|| e.getSource() == deleteButton && infoL.getText().equals("")|| e.getSource() == deleteButton && infoL.getText().equals("Search Panel")|| e.getSource() == deleteButton && infoL.getText().equals("Delete Panel")) {
      else if (e.getSource() == deleteButton && infoL.getText().equals("Delete Panel")) {
      if (panel.isVisible()) {
        b1.setVisible(false);
        b2.setVisible(false);
        b3.setVisible(false);
        okButton.setVisible(false);
        b4.setVisible(false);
        ISBN.setVisible(false);
        ISBNL.setVisible(false);
        title.setVisible(false);
        titleL.setVisible(false);
        author.setVisible(false);
        authorL.setVisible(false);
        genre.setVisible(false);
        genreL.setVisible(false);
        publication_year.setVisible(false);
        publication_yearL.setVisible(false);
        clearButton.setVisible(false);
        sp.setVisible(false);
        tb.setVisible(false);
        hideButton.setVisible(false);

        clearButton.setVisible(true);
        banner.setVisible(false);
        if (ifo.isEnabled()) {
          ifo.setVisible(true);
          ifo.setEnabled(false);
        } else if (!(ifo.isEnabled())) {
          ifo.setVisible(true);
          ifo.setEnabled(true);

        }
        ISBN.setVisible(true);
        ISBNL.setVisible(true);
        infoL.setText("Delete Panel");
        infoL.setVisible(true);
        banner.setVisible(false);

        try {
          int isid = Integer.parseInt(ISBN.getText());

          String sql = "DELETE FROM " + database_table + " WHERE ISBN=" + isid;
          statement.executeUpdate(sql);
          JOptionPane.showMessageDialog(this, " Record deleted ");
          ifo.setVisible(false);
          banner.setVisible(true);
          ISBN.setVisible(false);
          ISBNL.setVisible(false);
          ISBN.setText("");
          errorL.setVisible(false);
          errorL.setText("Error: Record not found");
          clearButton.setVisible(false);
        } catch (NumberFormatException e3) {
          if (!(ISBN.getText().equals(""))) {
            errorL.setText("Error: Enter The Right format");
            errorL.setVisible(true);

          }

          ISBN.setText("");

        } catch (Exception e2) {
          JOptionPane.showMessageDialog(this, "Error: record not deleted \n" + e2);

        }
      }
    }

    if (e.getSource() == clearButton) {
      publication_year.setText("");
      ISBN.setText("");
      author.setText("");
      title.setText("");
    }
    if (e.getSource() == exitButton) {
      System.exit(0);

    }
    if (e.getSource() == hideButton) {
      sp.setVisible(false);
      hideButton.setVisible(false);
    }
  }

}

class Start extends JFrame implements MouseListener, ActionListener, FocusListener {
  JButton button1, button2, button3, button4;
  JTextField userField;
  JPasswordField pass_Field, pass_Confirm;
  protected static String usernames[] = new String[1000];
  protected static String passwords[] = new String[1000];
  private static String path = "./src/"; //change this
  static int i = 0;

  boolean b = true;
  JPanel panel;

  JLabel labelU, labelP, Logo, labelS, labelI, labelB, labelC;

  public Start() {

    super("Library");

    Font f = new Font("monospaced", Font.BOLD, 22);
    Font g = new Font("monospaced", Font.BOLD, 20);
    Font b = new Font("monospaced", Font.BOLD, 36);
    Border bb = BorderFactory.createLineBorder(new Color(124, 62, 108), 7);
    Color back = new Color(17, 17, 31);
    Color fore = new Color(223, 106, 176);
    setSize(1915, 1080);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(null);
    getContentPane().setBackground(back);
    Logo = new JLabel(new ImageIcon(path + "logo.png"));
    userField = new JTextField("username");
    pass_Field = new JPasswordField("password");
    pass_Confirm = new JPasswordField("Confirm password");

    labelU = new JLabel(new ImageIcon(path + "test.png"));
    labelP = new JLabel(new ImageIcon(path + "lock(1).png"));
    labelC = new JLabel(new ImageIcon(path + "lock(1).png"));
    labelS = new JLabel(new ImageIcon(path + "eye1.png"));
    labelB = new JLabel("Log In");

    labelB.setFont(b);
    labelB.setForeground(back);
    labelI = new JLabel("Error: Incorrect password or username, try again");
    labelI.setFont(g);
    labelI.setBackground(Color.red);
    labelI.setForeground(Color.WHITE);
    labelI.setOpaque(true);
    labelI.setVisible(false);

    panel = new JPanel();
    panel.setSize(500, 570);
    panel.setBackground(fore);
    panel.setLayout(null);
    panel.setBorder(bb);
    panel.setLocation(700, 400);
    button1 = new JButton("Log In");
    button1.setFont(g);
    button1.setForeground(fore);
    button1.setBackground(back);
    button2 = new JButton("Exit");
    button2.setFont(g);
    button2.setForeground(fore);
    button2.setBackground(back);
    button3 = new JButton("Register");
    button3.setFont(g);
    button3.setForeground(fore);
    button3.setBackground(back);
    button4 = new JButton("Register");
    button4.setFont(g);
    button4.setForeground(fore);
    button4.setBackground(back);

    userField.setFont(f);
    pass_Field.setFont(f);
    pass_Confirm.setFont(f);

    userField.setBounds(850, 520, 220, 30);
    pass_Field.setBounds(850, 570, 220, 30);
    pass_Confirm.setBounds(850, 620, 220, 30);
    pass_Confirm.setVisible(false);

    button1.setBounds(850, 620, 220, 40);
    button3.setBounds(850, 680, 220, 40);
    button2.setBounds(850, 760, 220, 40);
    button4.setBounds(850, 680, 220, 40);
    button4.setVisible(false);
    Logo.setBounds(640, 0, 617, 400);
    labelB.setBounds(885, 300, 200, 300);
    labelU.setBounds(800, 515, 36, 36);
    labelP.setBounds(800, 565, 36, 36);
    labelC.setBounds(800, 615, 36, 36);
    labelC.setVisible(false);
    labelS.setBounds(750, 565, 36, 36);
    labelI.setBounds(700, 810, 600, 40);
    labelS.addMouseListener(this);
    button1.addActionListener(this);
    button2.addActionListener(this);
    button3.addActionListener(this);
    button4.addActionListener(this);
    panel.addMouseListener(this);

    getContentPane().add(button1);
    getContentPane().add(button2);
    getContentPane().add(button3);
    getContentPane().add(button4);
    getContentPane().add(userField);
    getContentPane().add(pass_Field);
    getContentPane().add(pass_Confirm);
    getContentPane().add(Logo);
    getContentPane().add(labelB);
    getContentPane().add(labelU);
    getContentPane().add(labelP);
    getContentPane().add(labelS);
    getContentPane().add(labelI);
    getContentPane().add(labelC);
    getContentPane().add(panel);

    userField.setForeground(Color.GRAY);
    pass_Field.setForeground(Color.GRAY);
    pass_Confirm.setForeground(Color.GRAY);

    userField.addFocusListener(this);
    pass_Field.addFocusListener(this);
    pass_Confirm.addFocusListener(this);

    setVisible(true);

  }
  @Override
  public void focusGained(FocusEvent e) {
    if (e.getSource() == userField) {
      if (userField.getText().equals("username")) {
        userField.setText("");
        userField.setForeground(Color.BLACK);
      }
    }
    if (e.getSource() == pass_Field) {
      if (String.valueOf(pass_Field.getPassword()).trim().equals("password")) {
        pass_Field.setText("");
        pass_Field.setForeground(Color.BLACK);

      }
    }
    if (e.getSource() == pass_Confirm) {
      if (String.valueOf(pass_Confirm.getPassword()).trim().equals("Confirm password")) {
        pass_Confirm.setText("");
        pass_Confirm.setForeground(Color.BLACK);
      }
    }
  }
  @Override
  public void focusLost(FocusEvent e) {
    if (e.getSource() == userField) {
      if (userField.getText().equals("")) {
        userField.setForeground(Color.GRAY);
        userField.setText("username");

      }
    }
    if (e.getSource() == pass_Field) {
      if (String.valueOf(pass_Field.getPassword()).trim().equals("")) {
        pass_Field.setForeground(Color.GRAY);
        pass_Field.setText("password");

      }
    }
    if (e.getSource() == pass_Confirm) {
      if (String.valueOf(pass_Confirm.getPassword()).trim().equals("")) {
        pass_Confirm.setForeground(Color.GRAY);
        pass_Confirm.setText("Confirm password");

      }
    }
  }

  public void actionPerformed(ActionEvent e) {
    String password = String.valueOf(pass_Field.getPassword()).trim();
    String username = "";
    String pass = "";

    if (e.getSource() == button1) {
      if (userField.getText().equals("2l5al") && password.equals("2l5al")) {
        this.dispose();
        new JavaBooks();
      } else {

        for (int i = 0; i < usernames.length; i++) {
          if (userField.getText().equals(Start.usernames[i]) && password.equals(Start.passwords[i])) {
            this.dispose();
            new JavaBooks();
          } else {
            labelI.setVisible(true);
          }
        }
      }
    }

    if (e.getSource() == button2) {
      System.exit(0);
    }
    if (e.getSource() == button3) {
      pass_Field.setEchoChar((char) 0);
      pass_Confirm.setEchoChar((char) 0);

      button1.setVisible(false);
      button3.setVisible(false);
      button4.setVisible(true);
      pass_Confirm.setVisible(true);
      labelC.setVisible(true);
      labelB.setText("Register");
      labelB.setBounds(870, 300, 200, 300);
      labelS.setBounds(750, 615, 36, 36);
    }
    if (e.getSource() == button4) {
      if (String.valueOf(pass_Field.getPassword()).trim().equals((String.valueOf(pass_Confirm.getPassword()).trim()))) {
        username = String.valueOf(userField.getText().trim());
        pass = String.valueOf(pass_Confirm.getPassword()).trim();
        button1.setVisible(true);
        button3.setVisible(true);
        button4.setVisible(false);
        userField.setText("");
        pass_Field.setText("");
        pass_Confirm.setText("");
        pass_Confirm.setVisible(false);
        labelC.setVisible(false);
        labelB.setText("Log In");
        labelB.setBounds(885, 300, 200, 300);
        labelS.setBounds(750, 565, 36, 36);
        labelI.setVisible(false);
        labelI = new JLabel("Error: Incorrect password or username, try again");
        Start.usernames[i] = username;
        Start.passwords[i] = pass;
        Start.i++;

      } else {
        labelI.setText("Error:password missmatch, try again");
        labelI.setVisible(true);
      }

    }

  }

  public static void main(String[] args) {
    //new Start();
    new JavaBooks();

  }

  @Override
  public void mousePressed(MouseEvent e) {
    if (e.getSource() == labelS) {
      if (b) {
        pass_Field.setEchoChar((char) 0);
        pass_Confirm.setEchoChar((char) 0);
        labelS.setIcon(new ImageIcon(path + "eye-crossed1.png"));
        b = false;
      } else if (!b) {
        pass_Field.setEchoChar('*');
        pass_Confirm.setEchoChar('*');
        labelS.setIcon(new ImageIcon(path + "eye1.png"));
        b = true;
      }
    }

  }

  @Override
  public void mouseClicked(MouseEvent e) {}

  @Override
  public void mouseReleased(MouseEvent e) {}

  @Override
  public void mouseEntered(MouseEvent e) {}

  @Override
  public void mouseExited(MouseEvent e) {}

}