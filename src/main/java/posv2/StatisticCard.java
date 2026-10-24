package posv2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatisticCard extends JPanel {

	private static final long serialVersionUID = 1L;

	 public StatisticCard(
	            String titulo,
	            String valor) {

	        setPreferredSize(
	                new Dimension(220,120)
	        );

	        setBorder(
	                BorderFactory
	                .createLineBorder(
	                        new Color(
	                                220,
	                                220,
	                                220)
	                )
	        );

	        setBackground(Color.WHITE);

	        setLayout(
	                new GridLayout(2,1)
	        );

	        JLabel lblTitulo =
	                new JLabel(
	                        titulo
	                );

	        JLabel lblValor =
	                new JLabel(
	                        valor
	                );

	        lblTitulo.setFont(
	                new Font(
	                        "Segoe UI",
	                        Font.PLAIN,
	                        16
	                )
	        );

	        lblValor.setFont(
	                new Font(
	                        "Segoe UI",
	                        Font.BOLD,
	                        28
	                )
	        );

	        add(lblTitulo);

	        add(lblValor);
	    }

}
