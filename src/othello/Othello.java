/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package othello;

//Para formas (Swing)
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

//Para layouts y dimensiones (AWT)
import java.awt.*;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Color;

// Para el manejo de eventos
import java.awt.event.ActionEvent;

//Para fuentes y bordes
import java.awt.Font;
import javax.swing.BorderFactory;



public class Othello extends javax.swing.JFrame{
    private static final int TAMANIO = 10;
    private JButton[][] Botones; // Matriz de botones
    private int[][] LogicaTablero; // Matriz para la lógica del juego
    private boolean TurnoJugador1; // true para blancas, false para negras
    
    // Variables para los jugadores
    private String NombreJugador1;
    private String NombreJugador2;
    private JLabel LabelTurno;
    private JPanel PanelInfo;
    
    //Variables del contador de fichas
    private JLabel ContadorBlancas;
    private JLabel ContadorNegras;
    private JPanel PanelContador;
    
    //Variables del contador de victorias
    private int VictoriasJugador1;
    private int VictoriasJugador2;
    private JLabel LabelVictorias;
    
    
    public Othello() {
        // Configuración básica de la ventana
        setTitle("Othello");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        InicializarTablero();
        
        //Variables de contador de victorias
        VictoriasJugador1 = 0;
        VictoriasJugador2 = 0;
        
        // Pedir nombres de jugadores
        PedirNombresJugadores();
        
        // Inicializar el turno
        TurnoJugador1 = true; // Empieza el jugador 1 (fichas blancas)
        
        // Crear el panel de información
        CrearPanelInfo();
        
        // Actualizar la etiqueta del turno
        ActualizarLabelTurno();
        
        //Contador de las fichas
        CrearPanelContador();
    }

    
    private void PedirNombresJugadores() {
        NombreJugador1 = JOptionPane.showInputDialog(this, //this, componente padre (ventana actual)
            "Ingrese el nombre del Jugador 1 (Fichas Blancas):", 
            "Nombre Jugador 1", 
            JOptionPane.QUESTION_MESSAGE);
        
        if (NombreJugador1 == null || NombreJugador1.trim().isEmpty()) { //Verifica nombre en blanco y si ingresaron espacios vacíos
            NombreJugador1 = "Jugador 1";                                //"trim()" elimina espacios al inicio y final del texto
        }
        
        NombreJugador2 = JOptionPane.showInputDialog(this, 
            "Ingrese el nombre del Jugador 2 (Fichas Negras):", 
            "Nombre Jugador 2", 
            JOptionPane.QUESTION_MESSAGE);
            
        if (NombreJugador2 == null || NombreJugador2.trim().isEmpty()) {
            NombreJugador2 = "Jugador 2";
        }
    }
    
    
    private void CrearPanelInfo() {
        PanelInfo = new JPanel();
        PanelInfo.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
        PanelInfo.setBackground(new Color(139, 69, 19));
        
        // Crear etiqueta para mostrar el turno
        LabelTurno = new JLabel();
        LabelTurno.setFont(new Font("Arial", Font.BOLD, 16));
        LabelTurno.setForeground(Color.WHITE);
        
        // Agregar etiqueta al panel
        PanelInfo.add(LabelTurno);
        
        // Agregar el panel de información al frame
        add(PanelInfo, BorderLayout.NORTH);
    }
    
    
    
    private void ActualizarLabelTurno() {
        String jugadorActual = TurnoJugador1 ? NombreJugador1 : NombreJugador2;
        String colorFichas = TurnoJugador1 ? "Blancas" : "Negras";
        LabelTurno.setText("Turno de: " + jugadorActual + " (" + colorFichas + ")");
    }
    
    
    
    private void ColocarFicha(int fila, int columna, int jugador) {
        LogicaTablero[fila][columna] = jugador;
    
        // Configuración común
        //botones[fila][columna].setBorderPainted(true);
        Botones[fila][columna].setFont(new Font("Arial Unicode MS", Font.BOLD, 40));
    
        if (jugador == 1) { // Ficha blanca
            Botones[fila][columna].setText("⭕");
            Botones[fila][columna].setForeground(Color.WHITE);
            // 3D para ficha blanca
            Botones[fila][columna].setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 2),
                BorderFactory.createRaisedBevelBorder()
            ));
        } 
        else { // Ficha negra
                Botones[fila][columna].setText("●");
                Botones[fila][columna].setForeground(Color.BLACK);
                // 3D para ficha negra
                Botones[fila][columna].setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(50, 50, 50), 2),
                BorderFactory.createLoweredBevelBorder()
            ));
        }
    
            // Mantener el color de fondo del tablero
            if ((fila + columna) % 2 == 0) {
                Botones[fila][columna].setBackground(new Color(205, 133, 63));
            } 
            else {
                Botones[fila][columna].setBackground(new Color(139, 69, 19));
            }
    }
    
 
    
    private void InicializarTablero() {
        // Crear panel para el tablero
        JPanel panelTablero = new JPanel();
        panelTablero.setLayout(new GridLayout(TAMANIO, TAMANIO, 1, 1)); // Agregamos pequeño espacio entre botones
        panelTablero.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelTablero.setBackground(new Color(139, 69, 19)); // Color marrón para el marco
    
        
        // Inicializar matrices
        Botones = new JButton[TAMANIO][TAMANIO];
        LogicaTablero = new int[TAMANIO][TAMANIO];
        
        for (int i = 0; i < TAMANIO; i++) {
            for (int j = 0; j < TAMANIO; j++) {
                Botones[i][j] = new JButton();
                Botones[i][j].setPreferredSize(new Dimension(50, 50));
                
                // Tablero de madera
                if ((i + j) % 2 == 0) {
                    Botones[i][j].setBackground(new Color(205, 133, 63)); // Marrón Claro
                } else {
                    Botones[i][j].setBackground(new Color(139, 69, 19)); // Marrón Oscuro
                }
                
                // Borde y configuración del botón
                Botones[i][j].setBorder(BorderFactory.createLineBorder(new Color(101, 67, 33), 1));
                Botones[i][j].setFocusPainted(false);
                
                // Agregar ActionListener a cada botón
                final int fila = i;
                final int columna = j;
                Botones[i][j].addActionListener((ActionEvent e) -> {
                    ManejarClick(fila, columna);
                });
                
                panelTablero.add(Botones[i][j]);
                LogicaTablero[i][j] = 0; // 0 = vacío, 1 = blancas, 2 = negras
            }
        }
        
        // Colocar las fichas iniciales en el centro
        int centro1 = TAMANIO/2 - 1;
        int centro2 = TAMANIO/2;
        
        // Configuración inicial del tablero
        ColocarFicha(centro1, centro1, 1); // Blanca
        ColocarFicha(centro2, centro2, 1); // Blanca
        ColocarFicha(centro1, centro2, 2); // Negra
        ColocarFicha(centro2, centro1, 2); // Negra
        
        // Agregar el panel al frame
        add(panelTablero);
        pack();
        setLocationRelativeTo(null); //Tablero al centro
    }
    
    
    
    
    //Direcciones a detectar
    private final int[][] DIRECCIONES = {
        {-1, -1}, {-1, 0}, {-1, 1},  // ↖  ↑  ↗
        {0, -1},           {0, 1},   // ←     →
        {1, -1},  {1, 0},  {1, 1}    // ↙  ↓  ↘
    };
    
    
    //Contador de las fichas y victorias
    private void CrearPanelContador() {
        // Crear el panel principal
        PanelContador = new JPanel();
        PanelContador.setLayout(new BoxLayout(PanelContador, BoxLayout.Y_AXIS));
        PanelContador.setBackground(new Color(139, 69, 19));
    
        // Panel para los contadores de fichas
        JPanel panelFichas = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        panelFichas.setBackground(new Color(139, 69, 19));
    
        // Contador para fichas blancas
        ContadorBlancas = new JLabel(NombreJugador1 + " (Blancas): 2");
        ContadorBlancas.setFont(new Font("Arial", Font.BOLD, 16));
        ContadorBlancas.setForeground(Color.WHITE);
    
        // Contador para fichas negras
        ContadorNegras = new JLabel(NombreJugador2 + " (Negras): 2");
        ContadorNegras.setFont(new Font("Arial", Font.BOLD, 16));
        ContadorNegras.setForeground(Color.WHITE);
    
        // Añadir contadores de fichas al panel de fichas
        panelFichas.add(ContadorBlancas);
        panelFichas.add(ContadorNegras);
    
        // Crear el label de victorias
        LabelVictorias = new JLabel();
        LabelVictorias.setFont(new Font("Arial", Font.BOLD, 14));
        LabelVictorias.setForeground(Color.WHITE);
        LabelVictorias.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        // Añadir todos los elementos al panel principal
        PanelContador.add(panelFichas);
        PanelContador.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio vertical
        PanelContador.add(LabelVictorias);
        PanelContador.add(Box.createRigidArea(new Dimension(0, 5))); // Espacio adicional al final
    
        // Actualizar el contador de victorias
        ActualizarContadorVictorias();
    
        // Añadir el panel al frame en la parte inferior
        add(PanelContador, BorderLayout.SOUTH);
    }
    
    
    private void ActualizarContadores() {
        int fichasBlancas = 0;
        int fichasNegras = 0;
    
        // Contar fichas en el tablero
        for (int i = 0; i < TAMANIO; i++) {
            for (int j = 0; j < TAMANIO; j++) {
                if (LogicaTablero[i][j] == 1) fichasBlancas++;
                else if (LogicaTablero[i][j] == 2) fichasNegras++;
            }
        }
    
        // Actualizar las etiquetas
        ContadorBlancas.setText(NombreJugador1 + " (Blancas): " + fichasBlancas);
        ContadorNegras.setText(NombreJugador2 + " (Negras): " + fichasNegras);
    }    
    
    //Contador exclusivo de las victorias
    private void ActualizarContadorVictorias() {
        LabelVictorias.setText(String.format("Victorias - %s: %d | %s: %d", 
            NombreJugador1, VictoriasJugador1, 
            NombreJugador2, VictoriasJugador2));
    }
    
    
    // Verifica si hay fichas para voltear en una dirección
    private boolean FichasParaVoltear(int fila, int columna, int dirFila, int dirColumna) {
        // Color actual y oponente
        int jugadorActual = TurnoJugador1 ? 1 : 2;
        int oponente = TurnoJugador1 ? 2 : 1;
        
        // Primera posición en la dirección
        int nuevaFila = fila + dirFila;
        int nuevaColumna = columna + dirColumna;
        
        // Contador de fichas que se pueden voltear
        int fichasParaVoltear = 0;
        
        // Mientras estemos dentro del tablero
        while (nuevaFila >= 0 && nuevaFila < TAMANIO && 
              nuevaColumna >= 0 && nuevaColumna < TAMANIO) {
            
            // Si encontramos una casilla vacía, no es válido
            if (LogicaTablero[nuevaFila][nuevaColumna] == 0) return false;
            
            // Si encontramos una ficha del oponente, seguir buscando
            if (LogicaTablero[nuevaFila][nuevaColumna] == oponente) {
                fichasParaVoltear++;
            }
            
            // Si encontramos una ficha propia
            if (LogicaTablero[nuevaFila][nuevaColumna] == jugadorActual) {
                // Es válido solo si hay fichas para voltear
                return fichasParaVoltear > 0;
            }
            
            // Seguir en la misma dirección
            nuevaFila += dirFila;
            nuevaColumna += dirColumna;
        }
        
        return false;
    }
       
    
    
    
    // Verifica si un movimiento es válido
    private boolean EsMovimientoValido(int fila, int columna) {
        // Si la casilla no está vacía, no es válido
        if (LogicaTablero[fila][columna] != 0) return false;
        
        // Verificar todas las direcciones
        for (int[] direccion : DIRECCIONES) {
            if (FichasParaVoltear(fila, columna, direccion[0], direccion[1])) {
                return true;
            }
        }
        return false;
    }
    
    
    
    // Verifica si hay movimientos válidos para el jugador actual
    private boolean HayMovimientosValidos() {
        for (int i = 0; i < TAMANIO; i++) {
            for (int j = 0; j < TAMANIO; j++) {
                if (LogicaTablero[i][j] == 0 && EsMovimientoValido(i, j)) {
                    return true;
                }
            }
        }
        return false;
    }
  
    
    
    // Voltea las fichas en una dirección
    private void VoltearFichas(int fila, int columna, int dirFila, int dirColumna) {
        int jugadorActual = TurnoJugador1 ? 1 : 2;
        int oponente = TurnoJugador1 ? 2 : 1;
        
        int nuevaFila = fila + dirFila;
        int nuevaColumna = columna + dirColumna;
        
        while (nuevaFila >= 0 && nuevaFila < TAMANIO && 
               nuevaColumna >= 0 && nuevaColumna < TAMANIO) {
            
                if (LogicaTablero[nuevaFila][nuevaColumna] == oponente) {
                    ColocarFicha(nuevaFila, nuevaColumna, jugadorActual);
                } 
                else if (LogicaTablero[nuevaFila][nuevaColumna] == jugadorActual) {
                    break;
                }
            
                nuevaFila += dirFila;
                nuevaColumna += dirColumna;
            }
    }
    
    
    
    // Realiza el movimiento y voltea las fichas
    private void RealizarMovimiento(int fila, int columna) {
        // Colocar la ficha en la posición seleccionada
        int jugadorActual = TurnoJugador1 ? 1 : 2;
        ColocarFicha(fila, columna, jugadorActual);
        
        // Voltear fichas en todas las direcciones válidas
        for (int[] direccion : DIRECCIONES) {
            if (FichasParaVoltear(fila, columna, direccion[0], direccion[1])) {
                VoltearFichas(fila, columna, direccion[0], direccion[1]);
            }
        }
        ActualizarContadores();
    }
    
    
    
    // Verifica si hay movimientos válidos para el oponente
    private boolean HayMovimientosValidosOponente() {
        TurnoJugador1 = !TurnoJugador1; // Cambiar temporalmente el turno
        boolean hayMovimientos = HayMovimientosValidos();
        TurnoJugador1 = !TurnoJugador1; // Restaurar el turno
        return hayMovimientos;
    }
    
    
    // Verifica si el juego ha terminado
    private boolean JuegoTerminado() {
        // Verificar si el tablero está lleno
        boolean tableroLleno = true;
        for (int i = 0; i < TAMANIO; i++) {
            for (int j = 0; j < TAMANIO; j++) {
                if (LogicaTablero[i][j] == 0) {
                    tableroLleno = false;
                    break;
                }
            }
        }
        
        // El juego termina si el tablero está lleno o no hay movimientos válidos para ningun jugador
        return tableroLleno || (!HayMovimientosValidos() && !HayMovimientosValidosOponente());
    }
   
    
    
    // Muestra el resultado final del juego
    private void MostrarResultadoFinal() {
        int fichasBlancas = 0;
        int fichasNegras = 0;
        
        // Contar fichas
        for (int i = 0; i < TAMANIO; i++) {
            for (int j = 0; j < TAMANIO; j++) {
                if (LogicaTablero[i][j] == 1) fichasBlancas++;
                else if (LogicaTablero[i][j] == 2) fichasNegras++;
            }
        }
        
        // Determinar ganador y actualizar contadores
        String mensaje;
        if (fichasBlancas > fichasNegras) {
            VictoriasJugador1++;
            mensaje = NombreJugador1 + " (Blancas) gana!\n" +
            "Blancas: " + fichasBlancas + " - Negras: " + fichasNegras;
        }
        
        else if (fichasNegras > fichasBlancas) {
            VictoriasJugador2++;
            mensaje = NombreJugador2 + " (Negras) gana!\n" +
            "Negras: " + fichasNegras + " - Blancas: " + fichasBlancas;
        }
        
        else {
            mensaje = "¡Empate!\n" +
            "Blancas: " + fichasBlancas + " - Negras: " + fichasNegras;
        }
        
        ActualizarContadorVictorias();
        
        //Mostrar resultado y preguntar si quieren jugar de nuevo
        int opcion = JOptionPane.showConfirmDialog(
            this,
            mensaje + "\n\n¿Desean jugar otra partida?",
            "Fin del Juego",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        
        if (opcion == JOptionPane.YES_OPTION) {
            ReiniciarTablero();
        }
        
        else {
            dispose(); // Cerrar la ventana
        }
        
    }
     
    
    
    private void ManejarClick(int fila, int columna) {
        System.out.println("Click en: [" + fila + "," + columna + "]");
        //Si la casilla está ocupada, no hacer nada
        if (LogicaTablero[fila][columna] != 0) return;
        
        // Verificar si es un movimiento válido
        if (EsMovimientoValido(fila, columna)) {
            RealizarMovimiento(fila, columna);
            // Cambiar turno
            TurnoJugador1 = !TurnoJugador1;
            // Actualizar la información del turno
            ActualizarLabelTurno();
            // Verificar si el juego ha terminado
            if (JuegoTerminado()) {
                MostrarResultadoFinal();
            }
            // Verificar si el siguiente jugador tiene movimientos válidos
            else if (!HayMovimientosValidos()) {
                // Si no hay movimientos válidos, cambiar turno
                TurnoJugador1 = !TurnoJugador1;
                ActualizarLabelTurno();
                JOptionPane.showMessageDialog(this, 
                "No hay movimientos válidos. Turno del siguiente jugador.");
            }
        } 
        
    }
    
    
    private void ReiniciarTablero() {
        // Limpiar el tablero
        for (int i = 0; i < TAMANIO; i++) {
            for (int j = 0; j < TAMANIO; j++) {
                LogicaTablero[i][j] = 0;
                Botones[i][j].setText("");
                Botones[i][j].setBorder(BorderFactory.createLineBorder(new Color(101, 67, 33), 1));
                
                // Restaurar colores del tablero
                if ((i + j) % 2 == 0) {
                    Botones[i][j].setBackground(new Color(205, 133, 63));
                } else {
                    Botones[i][j].setBackground(new Color(139, 69, 19));
                }
            }
        }
        
        // Colocar las fichas iniciales
        int centro1 = TAMANIO/2 - 1;
        int centro2 = TAMANIO/2;
        
        ColocarFicha(centro1, centro1, 1);
        ColocarFicha(centro2, centro2, 1);
        ColocarFicha(centro1, centro2, 2);
        ColocarFicha(centro2, centro1, 2);
        
        // Reiniciar el turno
        TurnoJugador1 = true;
        ActualizarLabelTurno();
        ActualizarContadores();
    }
   
    
  
    // Método main para ejecutar el juego
    public static void main(String args[]) {
        // Ejecutar en el Event Dispatch Thread
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Othello juego = new Othello();
                juego.setVisible(true);
            }
        });
    }
    
}