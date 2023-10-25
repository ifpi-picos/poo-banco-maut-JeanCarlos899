package br.edu.ifpi.poo.notifications;

public class NotificationsEmail implements Notifications {
    @Override
    public void sendNotification(String type, double value) {
        System.out.println("Enviando notificação por e-mail");
        System.out.println("Tipo: " + type);
        System.out.println("Valor: " + value);
    }
}
