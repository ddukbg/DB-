package dbteam;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;



public class app {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("Jihyunjung");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // to close normal
		frame.setResizable(false); // can't control frame size

		PrimaryPanel primary = new PrimaryPanel();
		frame.getContentPane().add(primary);

		frame.pack();
		frame.setVisible(true);

	}// main()

}// App class

class PrimaryPanel extends JPanel {

	private JPanel mainPanel;
	private JPanel tempPanel;
	private JComboBox cmbMenu;
	private String[] strMenu = { "CUSTOMER", "PRODUCT", "CUSORDER" };
	private JButton btnSearch;
	private JScrollPane jScollPane;
	private JTable jTable;

	JTextField txtCusWhere;
	JTextField txtCusID;
	JTextField txtCusName;
	JTextField txtAge;
	JTextField txtRank;
	JTextField txtJob;
	JTextField txtReserve;

	JTextField txtProdWhere;
	JTextField txtProdNo;
	JTextField txtProName;
	JTextField txtInvertory;
	JTextField txtPrice;
	JTextField txtManufacturer;

	JTextField txtCusOrderWhere;
	JTextField txtOrderNo;
	JTextField txtOrderCus;
	JTextField txtOrderPro;
	JTextField txtQuantity;
	JTextField txtAddress;
	JTextField txtOrderDate;

	private BtnListener btnL;

	private DatabaseConnect dbConnect;

	public PrimaryPanel() {
		// db connect
		dbConnect = new DatabaseConnect();

		// Listener
		btnL = new BtnListener();

		// primary panel
		setBackground(Color.white);
		setPreferredSize(new Dimension(1000, 640));
		setLayout(null);

		setPage();
	}

	public void setPage() {

		mainPanel = new JPanel();
		mainPanel.setBounds(100, 150, 800, 450);
		mainPanel.setBackground(new Color(245, 245, 245));
		mainPanel.setLayout(null);

		// combo box for choosing table
		cmbMenu = new JComboBox();
		for (String str : strMenu)
			cmbMenu.addItem(str);
		cmbMenu.setBounds(105, 105, 170, 40);
		cmbMenu.setFont(new Font("Verdana", Font.PLAIN, 18));
		add(cmbMenu);

		btnSearch = new JButton("Search");
		btnSearch.setBounds(285, 105, 80, 40);
		btnSearch.setFont(new Font("Verdana", Font.PLAIN, 13));
		btnSearch.addActionListener(btnL);
		add(btnSearch);

		add(mainPanel);
		setVisible(true);
	}

	private JPanel getCustomerTable(String condition) {
		JPanel jPanel = new JPanel();
		jPanel.setBounds(0, 0, 800, 450);
		jPanel.setBackground(new Color(245, 245, 245));
		jPanel.setLayout(null);

		/******* SELECT ********/
		JLabel lbSelect = new JLabel("SELECT");
		lbSelect.setBounds(80, 20, 100, 40);
		lbSelect.setFont(new Font("Verdana", Font.PLAIN, 17));
		jPanel.add(lbSelect);

		JLabel lbWhere = new JLabel("Where : ");
		lbWhere.setBounds(80, 60, 100, 40);
		lbWhere.setFont(new Font("Verdana", Font.PLAIN, 17));
		jPanel.add(lbWhere);

		txtCusWhere = new JTextField();
		txtCusWhere.setBounds(150, 65, 600, 30);
		txtCusWhere.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(txtCusWhere);

		// btnSelect
		JButton btnSelect = new JButton("Select");
		btnSelect.setBounds(350, 100, 80, 35);
		btnSelect.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnSelect.addActionListener(btnL);
		jPanel.add(btnSelect);

		/******* INSERT ********/
		JLabel lbInsert = new JLabel("INSERT");
		lbInsert.setBounds(80, 140, 100, 40);
		lbInsert.setFont(new Font("Verdana", Font.PLAIN, 17));
		jPanel.add(lbInsert);

		// cusID
		JLabel lbCusID = new JLabel("ID");
		lbCusID.setBounds(80, 170, 100, 40);
		lbCusID.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbCusID);

		txtCusID = new JTextField();
		txtCusID.setBounds(100, 180, 60, 30);
		jPanel.add(txtCusID);

		// cusName
		JLabel lbCusName = new JLabel("Name");
		lbCusName.setBounds(170, 170, 100, 40);
		lbCusName.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbCusName);

		txtCusName = new JTextField();
		txtCusName.setBounds(220, 180, 60, 30);
		jPanel.add(txtCusName);

		// age
		JLabel lbAge = new JLabel("Age");
		lbAge.setBounds(290, 170, 100, 40);
		lbAge.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbAge);

		txtAge = new JTextField();
		txtAge.setBounds(320, 180, 60, 30);
		jPanel.add(txtAge);

		// rank
		JLabel lbRank = new JLabel("Rank");
		lbRank.setBounds(390, 170, 100, 40);
		lbRank.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbRank);

		txtRank = new JTextField();
		txtRank.setBounds(430, 180, 60, 30);
		jPanel.add(txtRank);

		// job
		JLabel lbJob = new JLabel("Job");
		lbJob.setBounds(500, 170, 100, 40);
		lbJob.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbJob);

		txtJob = new JTextField();
		txtJob.setBounds(530, 180, 60, 30);
		jPanel.add(txtJob);

		// reserve
		JLabel lbReserve = new JLabel("Reserve");
		lbReserve.setBounds(600, 170, 100, 40);
		lbReserve.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbReserve);

		txtReserve = new JTextField();
		txtReserve.setBounds(665, 180, 60, 30);
		jPanel.add(txtReserve);

		// btnInsert
		JButton btnInsert = new JButton("Insert");
		btnInsert.setBounds(350, 220, 80, 35);
		btnInsert.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnInsert.addActionListener(btnL);
		jPanel.add(btnInsert);

		JTable jTable = new JTable();
		DefaultTableModel model = new DefaultTableModel();

		ArrayList<DescribeTableVO> arrDescribeTableVO = dbConnect.getTableDescription("Customer");
		Object[] columnsName = new Object[arrDescribeTableVO.size()];
		for (int i = 0; i < arrDescribeTableVO.size(); i++) {
			columnsName[i] = arrDescribeTableVO.get(i).getColumn_name();
		}
		model.setColumnIdentifiers(columnsName);

		Object[] rowData = new Object[arrDescribeTableVO.size()];
		System.out.println(arrDescribeTableVO.size());
		ArrayList<CustomerVO> arrCustomerVO = dbConnect.selectCustomer(condition);
		for (int i = 0; i < arrCustomerVO.size(); i++) {
			System.out.println(arrCustomerVO.get(i).getCusId());
			rowData[0] = arrCustomerVO.get(i).getCusId();
			rowData[1] = arrCustomerVO.get(i).getCusName();
			rowData[2] = arrCustomerVO.get(i).getAge();
			rowData[3] = arrCustomerVO.get(i).getRank();
			rowData[4] = arrCustomerVO.get(i).getJob();
			rowData[5] = arrCustomerVO.get(i).getReserve();

			model.addRow(rowData);
		}

		jTable.setModel(model);
		jTable.setPreferredScrollableViewportSize(jTable.getPreferredSize());
		jTable.setFillsViewportHeight(true);
		jTable.getTableHeader().setFont(new Font("Verdana", Font.PLAIN, 17));
		jTable.setFont(new Font("Verdana", Font.PLAIN, 17));
		jTable.setRowHeight(20);
		jTable.getTableHeader().setReorderingAllowed(false);

		jScollPane = new JScrollPane(jTable);
		jScollPane.setPreferredSize(new Dimension(650, 150));
		jScollPane.setBounds(75, 280, 650, 150);
		// jScollPane.getVerticalScrollBar().setValue(jScollPane.getVerticalScrollBar().getMaximum());

		jPanel.add(jScollPane);

		return jPanel;
	}

	private JPanel getProductTable(String condition) {
		JPanel jPanel = new JPanel();
		jPanel.setBounds(0, 0, 800, 450);
		jPanel.setBackground(new Color(245, 245, 245));
		jPanel.setLayout(null);

		/******* SELECT ********/
		JLabel lbSelect = new JLabel("SELECT");
		lbSelect.setBounds(80, 20, 100, 40);
		lbSelect.setFont(new Font("Verdana", Font.PLAIN, 17));
		jPanel.add(lbSelect);

		JLabel lbWhere = new JLabel("Where : ");
		lbWhere.setBounds(80, 60, 100, 40);
		lbWhere.setFont(new Font("Verdana", Font.PLAIN, 17));
		jPanel.add(lbWhere);

		txtProdWhere = new JTextField();
		txtProdWhere.setBounds(150, 65, 600, 30);
		txtProdWhere.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(txtProdWhere);

		// btnSelect
		JButton btnSelect = new JButton("Select");
		btnSelect.setBounds(350, 100, 80, 35);
		btnSelect.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnSelect.addActionListener(btnL);
		jPanel.add(btnSelect);

		/******* INSERT ********/
		JLabel lbInsert = new JLabel("INSERT");
		lbInsert.setBounds(80, 140, 100, 40);
		lbInsert.setFont(new Font("Verdana", Font.PLAIN, 17));
		jPanel.add(lbInsert);

		// prodNo
		JLabel lbProdNo = new JLabel("prodNo");
		lbProdNo.setBounds(80, 170, 100, 40);
		lbProdNo.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbProdNo);

		txtProdNo = new JTextField();
		txtProdNo.setBounds(140, 180, 60, 30);
		jPanel.add(txtProdNo);

		// prodName
		JLabel lbProdName = new JLabel("prodName");
		lbProdName.setBounds(210, 170, 100, 40);
		lbProdName.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbProdName);

		txtProName = new JTextField();
		txtProName.setBounds(290, 180, 60, 30);
		jPanel.add(txtProName);

		// invertory
		JLabel lbInvertory = new JLabel("invertory");
		lbInvertory.setBounds(360, 170, 100, 40);
		lbInvertory.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbInvertory);

		txtInvertory = new JTextField();
		txtInvertory.setBounds(430, 180, 60, 30);
		jPanel.add(txtInvertory);

		// price
		JLabel lbPrice = new JLabel("price");
		lbPrice.setBounds(500, 170, 100, 40);
		lbPrice.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbPrice);

		txtPrice = new JTextField();
		txtPrice.setBounds(540, 180, 60, 30);
		jPanel.add(txtPrice);

		// rank
		JLabel lbRank = new JLabel("manufacturer");
		lbRank.setBounds(610, 170, 100, 40);
		lbRank.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbRank);

		txtManufacturer = new JTextField();
		txtManufacturer.setBounds(710, 180, 60, 30);
		jPanel.add(txtManufacturer);

		// btnInsert
		JButton btnInsert = new JButton("Insert");
		btnInsert.setBounds(350, 220, 80, 35);
		btnInsert.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnInsert.addActionListener(btnL);
		jPanel.add(btnInsert);
		JTable jTable = new JTable();
		DefaultTableModel model = new DefaultTableModel();

		ArrayList<DescribeTableVO> arrDescribeTableVO = dbConnect.getTableDescription("Product");
		Object[] columnsName = new Object[arrDescribeTableVO.size()];
		for (int i = 0; i < arrDescribeTableVO.size(); i++) {
			columnsName[i] = arrDescribeTableVO.get(i).getColumn_name();
		}
		model.setColumnIdentifiers(columnsName);

		Object[] rowData = new Object[arrDescribeTableVO.size()];
		ArrayList<ProductVO> arrProductVO = dbConnect.selectProduct(condition);
		for (int i = 0; i < arrProductVO.size(); i++) {
			rowData[0] = arrProductVO.get(i).getProdNo();
			rowData[1] = arrProductVO.get(i).getProdName();
			rowData[2] = arrProductVO.get(i).getInvertory();
			rowData[3] = arrProductVO.get(i).getPrice();
			rowData[4] = arrProductVO.get(i).getManzufacturer();

			model.addRow(rowData);
		}

		jTable.setModel(model);
		jTable.setPreferredScrollableViewportSize(jTable.getPreferredSize());
		jTable.setFillsViewportHeight(true);
		jTable.getTableHeader().setFont(new Font("Verdana", Font.PLAIN, 17));
		jTable.setFont(new Font("Verdana", Font.PLAIN, 17));
		jTable.setRowHeight(20);

		jScollPane = new JScrollPane(jTable);
		jScollPane.setPreferredSize(new Dimension(650, 150));
		jScollPane.setBounds(75, 280, 650, 150);
		// jScollPane.getVerticalScrollBar().setValue(jScollPane.getVerticalScrollBar().getMaximum());

		jPanel.add(jScollPane);

		return jPanel;
	}

	private JPanel getCusOrderTable(String condition) {
		JPanel jPanel = new JPanel();
		jPanel.setBounds(0, 0, 800, 450);
		jPanel.setBackground(new Color(245, 245, 245));
		jPanel.setLayout(null);

		/******* SELECT ********/
		JLabel lbSelect = new JLabel("SELECT");
		lbSelect.setBounds(80, 20, 100, 40);
		lbSelect.setFont(new Font("Verdana", Font.PLAIN, 17));
		jPanel.add(lbSelect);

		JLabel lbWhere = new JLabel("Where : ");
		lbWhere.setBounds(80, 60, 100, 40);
		lbWhere.setFont(new Font("Verdana", Font.PLAIN, 17));
		jPanel.add(lbWhere);

		txtCusOrderWhere = new JTextField();
		txtCusOrderWhere.setBounds(150, 65, 600, 30);
		txtCusOrderWhere.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(txtCusOrderWhere);

		// btnSelect
		JButton btnSelect = new JButton("Select");
		btnSelect.setBounds(350, 100, 80, 35);
		btnSelect.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnSelect.addActionListener(btnL);
		jPanel.add(btnSelect);

		/******* INSERT ********/
		JLabel lbInsert = new JLabel("INSERT");
		lbInsert.setBounds(80, 140, 100, 40);
		lbInsert.setFont(new Font("Verdana", Font.PLAIN, 17));
		jPanel.add(lbInsert);

		// orderNo
		JLabel lbCusID = new JLabel("No.");
		lbCusID.setBounds(80, 170, 100, 40);
		lbCusID.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbCusID);

		txtOrderNo = new JTextField();
		txtOrderNo.setBounds(110, 180, 60, 30);
		jPanel.add(txtOrderNo);

		// orderCus
		JLabel lbCusName = new JLabel("Cus");
		lbCusName.setBounds(180, 170, 100, 40);
		lbCusName.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbCusName);

		txtOrderCus = new JTextField();
		txtOrderCus.setBounds(210, 180, 60, 30);
		jPanel.add(txtOrderCus);

		// orderPro
		JLabel lbAge = new JLabel("Prod");
		lbAge.setBounds(280, 170, 100, 40);
		lbAge.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbAge);

		txtOrderPro = new JTextField();
		txtOrderPro.setBounds(320, 180, 60, 30);
		jPanel.add(txtOrderPro);

		// quantity
		JLabel lbRank = new JLabel("quantity");
		lbRank.setBounds(390, 170, 100, 40);
		lbRank.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbRank);

		txtQuantity = new JTextField();
		txtQuantity.setBounds(455, 180, 60, 30);
		jPanel.add(txtQuantity);

		// address
		JLabel lbJob = new JLabel("address");
		lbJob.setBounds(525, 170, 100, 40);
		lbJob.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbJob);

		txtAddress = new JTextField();
		txtAddress.setBounds(580, 180, 60, 30);
		jPanel.add(txtAddress);

		// orderDate
		JLabel lbReserve = new JLabel("orderDate");
		lbReserve.setBounds(650, 170, 100, 40);
		lbReserve.setFont(new Font("Verdana", Font.PLAIN, 15));
		jPanel.add(lbReserve);

		txtOrderDate = new JTextField();
		txtOrderDate.setBounds(720, 180, 60, 30);
		jPanel.add(txtOrderDate);

		// btnInsert
		JButton btnInsert = new JButton("Insert");
		btnInsert.setBounds(350, 220, 80, 35);
		btnInsert.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnInsert.addActionListener(btnL);
		jPanel.add(btnInsert);

		JTable jTable = new JTable();
		DefaultTableModel model = new DefaultTableModel();

		ArrayList<DescribeTableVO> arrDescribeTableVO = dbConnect.getTableDescription("CusOrder");
		Object[] columnsName = new Object[arrDescribeTableVO.size()];
		for (int i = 0; i < arrDescribeTableVO.size(); i++) {
			columnsName[i] = arrDescribeTableVO.get(i).getColumn_name();
		}
		model.setColumnIdentifiers(columnsName);

		Object[] rowData = new Object[arrDescribeTableVO.size()];
		ArrayList<CusOrderVO> arrCusOrderVO = dbConnect.selectCusOrder(condition);
		for (int i = 0; i < arrCusOrderVO.size(); i++) {
			rowData[0] = arrCusOrderVO.get(i).getOrderNo();
			rowData[1] = arrCusOrderVO.get(i).getOrderCus();
			rowData[2] = arrCusOrderVO.get(i).getOrderPro();
			rowData[3] = arrCusOrderVO.get(i).getQuantity();
			rowData[4] = arrCusOrderVO.get(i).getAddress();
			rowData[5] = arrCusOrderVO.get(i).getOrderDate();

			model.addRow(rowData);
		}

		jTable.setModel(model);
		jTable.setPreferredScrollableViewportSize(jTable.getPreferredSize());
		jTable.setFillsViewportHeight(true);
		jTable.getTableHeader().setFont(new Font("Verdana", Font.PLAIN, 17));
		jTable.setFont(new Font("Verdana", Font.PLAIN, 17));
		jTable.setRowHeight(20);
		jTable.getTableHeader().setReorderingAllowed(false);

		jScollPane = new JScrollPane(jTable);
		jScollPane.setPreferredSize(new Dimension(650, 150));
		jScollPane.setBounds(75, 280, 650, 150);
		// jScollPane.getVerticalScrollBar().setValue(jScollPane.getVerticalScrollBar().getMaximum());

		jPanel.add(jScollPane);

		return jPanel;
	}

	private class BtnListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {

			JButton b = (JButton) event.getSource();
			String tableName;
			String condition = "";

			String btnName = null;
			btnName = b.getText();
			switch (btnName) {
			case "Search":
				mainPanel.removeAll();
				mainPanel.setLayout(null);

				tableName = strMenu[cmbMenu.getSelectedIndex()];
				condition = "";
				jTable = null;
				tempPanel = null;

				switch (tableName) {
				case "CUSTOMER":
					tempPanel = getCustomerTable(condition);
					break;
				case "PRODUCT":
					tempPanel = getProductTable(condition);
					break;
				case "CUSORDER":
					tempPanel = getCusOrderTable(condition);
					break;
				}

				mainPanel.add(tempPanel);
				add(mainPanel);

				setVisible(true);
				validate();
				repaint();
				break;
			case "Insert":
				mainPanel.removeAll();
				mainPanel.setLayout(null);

				tableName = strMenu[cmbMenu.getSelectedIndex()];
				jTable = null;
				tempPanel = null;

				switch (tableName) {
				case "CUSTOMER":
					dbConnect.insertCustomer(txtCusID.getText(), txtCusName.getText(),
							Integer.parseInt(txtAge.getText()), txtRank.getText(), txtJob.getText(),
							Integer.parseInt(txtReserve.getText()));
					tempPanel = getCustomerTable(condition);
					break;
				case "PRODUCT":
					dbConnect.insertProduct(txtProdNo.getText(), txtProName.getText(),
							Integer.parseInt(txtInvertory.getText()), Integer.parseInt(txtPrice.getText()),
							txtManufacturer.getText());
					tempPanel = getProductTable(condition);
					break;
				case "CUSORDER":
					dbConnect.insertCusOrder(txtOrderNo.getText(), txtOrderCus.getText(), txtOrderPro.getText(),
							Integer.parseInt(txtQuantity.getText()), txtAddress.getText(), txtOrderDate.getText());
					tempPanel = getCusOrderTable(condition);
					break;
				}

				mainPanel.add(tempPanel);
				add(mainPanel);

				setVisible(true);
				validate();
				repaint();
				break;

			case "Select":
				mainPanel.removeAll();
				mainPanel.setLayout(null);

				tableName = strMenu[cmbMenu.getSelectedIndex()];
				jTable = null;
				tempPanel = null;

				switch (tableName) {
				case "CUSTOMER":
					condition = "where ";
					condition += txtCusWhere.getText();
					tempPanel = getCustomerTable(condition);
					break;
				case "PRODUCT":
					condition = "where ";
					condition += txtProdWhere.getText();
					tempPanel = getProductTable(condition);
					break;
				case "CUSORDER":
					condition = "where ";
					condition += txtCusOrderWhere.getText();
					tempPanel = getCusOrderTable(condition);
					break;
				}

				mainPanel.add(tempPanel);
				add(mainPanel);

				setVisible(true);
				validate();
				repaint();
				break;

			}
		}
	} // BtnListener class
} // PrimaryPanel class

// DB
class DatabaseConnect {
	private Connection conn = null;
	private Statement stmt = null;

	private static final String USERNAME = "s14011014";
	private static final String PASSWORD = "rlaskfk1";
	private static final String URL 
	= "jdbc:oracle:thin::@203.250.148.93:1074:orcl";

	public DatabaseConnect() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			System.out.println("DB Connection OK!");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("DB Driver Error!");
		}catch(SQLException se) {
			se.printStackTrace();
			System.out.println("DB Connection Error!");
			
		}
	}

	public ArrayList<CustomerVO> selectCustomer(String condition) {
		String sql = "select * from Customer ";
		sql += condition;
		PreparedStatement pstmt = null;
		ArrayList<CustomerVO> arrCustomerVO = new ArrayList<CustomerVO>();

		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				CustomerVO tempCustomerVO = new CustomerVO(rs.getString("cusID"), rs.getString("cusName"),
						rs.getInt("age"), rs.getString("rank"), rs.getString("job"), rs.getInt("reserve"));
				arrCustomerVO.add(tempCustomerVO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed())
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return arrCustomerVO;
	}

	public ArrayList<ProductVO> selectProduct(String condition) {
		String sql = "select * from Product ";
		sql += condition;
		PreparedStatement pstmt = null;
		ArrayList<ProductVO> arrProductVO = new ArrayList<ProductVO>();

		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				ProductVO tempProductVO = new ProductVO(rs.getString("prodNo"), rs.getString("prodName"),
						rs.getInt("invertory"), rs.getInt("price"), rs.getString("manufacturer"));
				arrProductVO.add(tempProductVO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed())
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return arrProductVO;
	}

	public ArrayList<CusOrderVO> selectCusOrder(String condition) {
		String sql = "select * from CusOrder ";
		sql += condition;
		PreparedStatement pstmt = null;
		ArrayList<CusOrderVO> arrCusOrderVO = new ArrayList<CusOrderVO>();

		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				CusOrderVO tempCustomerVO = new CusOrderVO(rs.getString("orderNo"), rs.getString("orderCus"),
						rs.getString("orderPro"), rs.getInt("quantity"), rs.getString("address"),
						rs.getString("orderDate"));
				arrCusOrderVO.add(tempCustomerVO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed())
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return arrCusOrderVO;
	}

	public ArrayList<DescribeTableVO> getTableDescription(String tableName) {
		String sql = "select COLUMN_NAME from COLS where table_name=?";
		PreparedStatement pstmt = null;
		ArrayList<DescribeTableVO> arrDescribeTableVO = new ArrayList<DescribeTableVO>();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tableName.toUpperCase());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.println("getTableDescription");
				System.out.println(rs.getString("COLUMN_NAME"));
				DescribeTableVO tempDescribeTableVO = new DescribeTableVO(rs.getString("COLUMN_NAME"));
				arrDescribeTableVO.add(tempDescribeTableVO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed())
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return arrDescribeTableVO;
	}

	public void insertCustomer(String cusID, String cusName, int age, String rank, String job, int reserve) {
		String sql = "insert into Customer values (?,?,?,?,?,?)";
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cusID);
			pstmt.setString(2, cusName);
			pstmt.setInt(3, age);
			pstmt.setString(4, rank);
			pstmt.setString(5, job);
			pstmt.setInt(6, reserve);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed())
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void insertProduct(String prodNo, String prodName, int invertory, int price, String manufacturer) {
		String sql = "insert into Product values (?,?,?,?,?)";
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, prodNo);
			pstmt.setString(2, prodName);
			pstmt.setInt(3, invertory);
			pstmt.setInt(4, price);
			pstmt.setString(5, manufacturer);
			pstmt.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed())
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void insertCusOrder(String orderNo, String orderCus, String orderPro, int quantity, String address,
			String orderDate) {
		String sql = "insert into CusOrder values (?,?,?,?,?,?)";
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, orderNo);
			pstmt.setString(2, orderCus);
			pstmt.setString(3, orderPro);
			pstmt.setInt(4, quantity);
			pstmt.setString(5, address);
			pstmt.setString(6, orderDate);
			pstmt.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed())
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}// DatabaseConnect

class DescribeTableVO {
	private String column_name;

	public DescribeTableVO(String column_name) {
		super();
		this.column_name = column_name;
	}

	public String getColumn_name() {
		return column_name;
	}

	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}

	@Override
	public String toString() {
		return "DescribeTableVO [column_name=" + column_name + "]";
	}

}// DescribeTableVO

class ProductVO {
	private String prodNo;
	private String prodName;
	private int invertory;
	private int price;
	private String manzufacturer;

	public ProductVO() {

	}

	public ProductVO(String prodNo, String prodName, int invertory, int price, String manzufacturer) {
		super();
		this.prodNo = prodNo;
		this.prodName = prodName;
		this.invertory = invertory;
		this.price = price;
		this.manzufacturer = manzufacturer;
	}

	public String getProdNo() {
		return prodNo;
	}

	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public int getInvertory() {
		return invertory;
	}

	public void setInvertory(int invertory) {
		this.invertory = invertory;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getManzufacturer() {
		return manzufacturer;
	}

	public void setManzufacturer(String manzufacturer) {
		this.manzufacturer = manzufacturer;
	}

	@Override
	public String toString() {
		return "ProductVO [prodNo=" + prodNo + ", prodName=" + prodName + ", invertory=" + invertory + ", price="
				+ price + ", manzufacturer=" + manzufacturer + "]";
	}
}// ProductVO

class CusOrderVO {
	private String orderNo;
	private String orderCus;
	private String orderPro;
	private int quantity;
	private String address;
	private String orderDate;

	public CusOrderVO() {

	}

	public CusOrderVO(String orderNo, String orderCus, String orderPro, int quantity, String address,
			String orderDate) {
		super();
		this.orderNo = orderNo;
		this.orderCus = orderCus;
		this.orderPro = orderPro;
		this.quantity = quantity;
		this.address = address;
		this.orderDate = orderDate;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderCus() {
		return orderCus;
	}

	public void setOrderCus(String orderCus) {
		this.orderCus = orderCus;
	}

	public String getOrderPro() {
		return orderPro;
	}

	public void setOrderPro(String orderPro) {
		this.orderPro = orderPro;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	@Override
	public String toString() {
		return "CusOrderVO [orderNo=" + orderNo + ", orderCus=" + orderCus + ", orderPro=" + orderPro + ", quantity="
				+ quantity + ", address=" + address + ", orderDate=" + orderDate + "]";
	}
}// CusOrderVO

class CustomerVO {
	private String cusId;
	private String cusName;
	private int age;
	private String rank;
	private String job;
	private int reserve;

	public CustomerVO() {

	}

	public CustomerVO(String cusId, String cusName, int age, String rank, String job, int reserve) {
		super();
		this.cusId = cusId;
		this.cusName = cusName;
		this.age = age;
		this.rank = rank;
		this.job = job;
		this.reserve = reserve;
	}

	public String getCusId() {
		return cusId;
	}

	public void setCusId(String cusId) {
		this.cusId = cusId;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public int getReserve() {
		return reserve;
	}

	public void setReserve(int reserve) {
		this.reserve = reserve;
	}

	@Override
	public String toString() {
		return "CustomerVO [cusId=" + cusId + ", cusName=" + cusName + ", age=" + age + ", rank=" + rank + ", job="
				+ job + ", reserve=" + reserve + "]";
	}
}// CustomerVO