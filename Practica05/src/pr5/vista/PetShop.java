package pr5.vista;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import pr5.controlador.Controlador;

public class PetShop extends JFrame {
	private static final long serialVersionUID = 6181681095695644006L;
	
	private JPanel contentPane;
	private JTextField tfCodigo;
	private JList<String> listaMascotas;
	private Controlador controlador;
	private JList<String> listaComplementos;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PetShop frame = new PetShop();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PetShop() {
		final PetShop yo = this;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 447);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTiendaDeMascotas = new JLabel("TIENDA DE MASCOTAS");
		lblTiendaDeMascotas.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTiendaDeMascotas.setHorizontalAlignment(SwingConstants.CENTER);
		lblTiendaDeMascotas.setBounds(10, 11, 564, 14);
		contentPane.add(lblTiendaDeMascotas);
		
		JButton btnIntroducirMascota = new JButton("Introducir Mascota");
		btnIntroducirMascota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirVentanaCrear(true);
			}
		});
		btnIntroducirMascota.setBounds(10, 36, 171, 43);
		contentPane.add(btnIntroducirMascota);
		
		JButton btnIntroducirComplemento = new JButton("Introducir Complemento");
		btnIntroducirComplemento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirVentanaCrear(false);
			}
		});
		btnIntroducirComplemento.setBounds(403, 36, 171, 43);
		contentPane.add(btnIntroducirComplemento);
		
		JButton btnModificarProducto = new JButton("Modificar Producto");
		btnModificarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.abrirVentanaModificar(contentPane, yo, listaMascotas, listaComplementos);
			}
		});
		btnModificarProducto.setBounds(10, 90, 171, 43);
		contentPane.add(btnModificarProducto);
		
		JButton btnEliminarProducto = new JButton("Eliminar Producto");
		btnEliminarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.elminarArticulo(contentPane, yo, listaMascotas, listaComplementos);
			}
		});
		btnEliminarProducto.setBounds(403, 90, 171, 43);
		contentPane.add(btnEliminarProducto);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 173, 564, 94);
		contentPane.add(scrollPane);
		
		listaMascotas = new JList<String>();
		listaMascotas.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if(listaMascotas.getSelectedIndex() == -1) {
					return;
				}
				listaComplementos.clearSelection();
			}
		});
		listaMascotas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaMascotas.setModel(new DefaultListModel<String>());
		scrollPane.setViewportView(listaMascotas);
		
		tfCodigo = new JTextField();
		tfCodigo.setBounds(196, 113, 98, 20);
		contentPane.add(tfCodigo);
		tfCodigo.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buscarArticulos();
			}
		});
		btnBuscar.setBounds(304, 110, 89, 23);
		contentPane.add(btnBuscar);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setBounds(196, 90, 46, 14);
		contentPane.add(lblCdigo);
		
		JLabel lblMascotas = new JLabel("Mascotas");
		lblMascotas.setBounds(10, 148, 564, 14);
		contentPane.add(lblMascotas);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 303, 564, 94);
		contentPane.add(scrollPane_1);
		
		listaComplementos = new JList<String>();
		listaComplementos.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if(listaComplementos.getSelectedIndex() == -1) {
					return;
				}
				listaMascotas.clearSelection();
			}
		});
		listaComplementos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaComplementos.setModel(new DefaultListModel<String>());
		scrollPane_1.setViewportView(listaComplementos);
		
		JLabel lblComplementos = new JLabel("Complementos");
		lblComplementos.setBounds(10, 278, 564, 14);
		contentPane.add(lblComplementos);
		
		controlador = Controlador.getInstance();
		buscarArticulos();
		
		addWindowListener(new WindowAdapter() {
        	public void windowClosing(WindowEvent e) {
        		controlador.guardarArticulos();
        		dispose();
        		System.exit(0);
        	}
        });
	}
	
	private void abrirVentanaCrear(boolean mascota) {
		new VentanaCrear(this, mascota);
		this.setVisible(false);
	}
	
	public void abrirVentanaModificar(long codigo, boolean mascota) {
		new VentanaModificar(this, codigo, mascota);
		this.setVisible(false);
	}
	
	public void buscarArticulos() {
		controlador.buscarArticulo(listaMascotas, listaComplementos, tfCodigo);
	}
}
