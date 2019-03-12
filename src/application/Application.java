package application;

import screens.InitScreen;

import javax.swing.*;

public class Application {
    public static void main(String args[]) {

        /**
         * Adiciona o look and feel Nimbus
         */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (UnsupportedLookAndFeelException e) {
            // handle exception
        } catch (ClassNotFoundException e) {
            // handle exception
        } catch (InstantiationException e) {
            // handle exception
        } catch (IllegalAccessException e) {
            // handle exception
        }
        InitScreen initScreen = new InitScreen();  //Instância uma tela InitScreen
    }
}
