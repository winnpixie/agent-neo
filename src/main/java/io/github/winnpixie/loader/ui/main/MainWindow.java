package io.github.winnpixie.loader.ui.main;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import io.github.winnpixie.loader.AgentSmith;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MainWindow extends JFrame {
    private final int windowWidth;
    private final int windowHeight;

    private final JTextField agentPathField = new JTextField();
    private final JButton selectAgentPathButton = new JButton("Select");
    private final JButton refreshJvmListButton = new JButton("Refresh JVM List");
    private final JTextArea jvmListArea = new JTextArea();
    private final JTextField jvmIdField = new JTextField();
    private final JButton attachButton = new JButton("Attach to JVM");

    public MainWindow(int width, int height) {
        super("Agent Smith - Java Agent Attacher");

        this.windowWidth = width;
        this.windowHeight = height;

        buildWindow();
        populateWindow();
        packAndDisplay();
    }

    private void buildWindow() {
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setPreferredSize(new Dimension(windowWidth, windowHeight));
        super.setResizable(false);
        super.setLayout(null);
    }

    private void populateWindow() {
        agentPathField.setBounds(5, 5, 480, 30);
        agentPathField.setEditable(false);
        super.add(agentPathField);

        selectAgentPathButton.setBounds(490, 5, 130, 30);
        selectAgentPathButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir", "."));
            if (fileChooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) return;

            File agentFile = fileChooser.getSelectedFile();
            if (agentFile == null) return;

            try {
                agentPathField.setText(agentFile.getCanonicalPath());
            } catch (IOException ex) {
                ex.printStackTrace();

                agentPathField.setText(agentFile.getAbsolutePath());
            }
        });
        super.add(selectAgentPathButton);

        refreshJvmListButton.setBounds(5, 40, 615, 30);
        refreshJvmListButton.addActionListener(e -> {
            jvmListArea.setText("Id - Name\n\n");

            VirtualMachine.list().forEach(vmd -> jvmListArea.append(String.format("%s - %s%n", vmd.id(), vmd.displayName())));
        });
        super.add(refreshJvmListButton);

        jvmListArea.setBounds(5, 75, 615, 325);
        jvmListArea.setEditable(false);
        JScrollPane jvmScrollPane = new JScrollPane(jvmListArea);
        jvmScrollPane.setBounds(jvmListArea.getBounds());
        super.add(jvmScrollPane);

        jvmIdField.setBounds(5, 405, 480, 30);
        super.add(jvmIdField);

        attachButton.setBounds(490, 405, 130, 30);
        attachButton.addActionListener(e -> {
            try {
                VirtualMachine vm = VirtualMachine.attach(jvmIdField.getText());
                vm.loadAgent(agentPathField.getText());
                AgentSmith.LOGGER.info(String.format("Attached to JVM Id %s", vm.id()));
                vm.detach();
            } catch (AttachNotSupportedException | IOException | AgentLoadException | AgentInitializationException ex) {
                ex.printStackTrace();
            }
        });
        super.add(attachButton);
    }

    private void packAndDisplay() {
        super.pack();
        super.setVisible(true);
        super.setLocationRelativeTo(null);
    }
}
