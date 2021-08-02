package controle;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import fases.*;
import classesPrincipais.*;

public class ControleDeTela implements ActionListener {
	
	Container con;
	
	Menu menu;
	
	Recordes recordes;
	Creditos creditos;
	
	private boolean menuOn, recordesOn, creditosOn;
	
	public ControleDeTela(Container con) {

		this.con = con;		
		
		menu = new Menu();
		recordes = new Recordes();
		creditos = new Creditos();
		
		con.add(menu.getPanel(1));
		con.add(menu.getPanel(2));
		con.add(recordes);
		con.add(creditos);
		
//		recordes.setVisible(false);
//		creditos.setVisible(false);
		
		menuOn = true;
		recordesOn = false;
		creditosOn = false;
		
		alocaMenu();
	}

//-----------------------------------------------------Alocando-------------------------------------------------------------//
	
	public void alocaMenu() {//----------------MENU
		menu.getPanel(1).setVisible(true);
		menu.getPanel(2).setVisible(true);
		
		creditos.setVisible(false);
		recordes.setVisible(false);
		menu.setVisible(true);
		
		setFalse();
		menuOn = true;
		
		menu.getBotao(1).addActionListener(this);
		menu.getBotao(2).addActionListener(this);
		menu.getBotao(3).addActionListener(this);
		menu.getBotao(4).addActionListener(this);
		menu.getBotao(5).addActionListener(this);
	}
	public void alocaCreditos() {//------------CREDITOS
		menu.getPanel(1).setVisible(false);
		menu.getPanel(2).setVisible(false);
		
		setFalse();
		creditosOn = true;
		
		creditos.getBotao().addActionListener(this);//Botao de Voltar
		creditos.setVisible(true);
	}
	public void alocaRecordes() {//------------RECORDES
		menu.getPanel(1).setVisible(false);
		menu.getPanel(2).setVisible(false);
		
		setFalse();
		recordesOn = true;
		
		recordes.getBotao().addActionListener(this);//Botao de Voltar
		recordes.setVisible(true);
	}
	public void setFalse() {//-----------------ALL FALSE
		menuOn = false;
		creditosOn = false;
		recordesOn = false;
	}

//-------------------------------------------------Action Performed---------------------------------------------------------//
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(menuOn) {
			if(e.getSource() == menu.getBotao(1)) {//Novo Jogo
				System.out.println("Iniciar");
				/*
				
				fases = new Fases(1);
				fases.setLayout(null);
				con.add(fases);
				fases.requestFocusInWindow();
		        fases.setFocusable(true);
		        */
			}
			if(e.getSource() == menu.getBotao(2)) {//Carregar
				System.out.println("Carregar");
				/*
				
				fases = new Fases(2);
				fases.setLayout(null);
				con.add(fases);
				fases.requestFocusInWindow();
		        fases.setFocusable(true);
		        */
			}
			if(e.getSource() == menu.getBotao(3)) {//Recordes
				alocaRecordes();
			}
			if(e.getSource() == menu.getBotao(4)) {//Creditos	
				alocaCreditos();
			}
			if(e.getSource() == menu.getBotao(5)) {//Sair
				System.exit(0);
			}
			
		} 
		else if(recordesOn) {//Botao 'Voltar' de Recordes
			if(e.getSource() == recordes.getBotao()) {
				System.out.println("Voltar");
				alocaMenu();
			}
		} 
		else if(creditosOn) {//Botao 'Voltar' de Creditos
			if(e.getSource() == creditos.getBotao()) {
				System.out.println("Voltar");
				alocaMenu();
			}
		}
	}

}
