package br.edu.ifpi.poo.notifications;

public class NotificationsSms implements Notifications {
    @Override
    public void sendNotification(String type, double value) {
        System.out.println("Enviando notificação por SMS");
        System.out.println("Tipo: " + type);
        System.out.println("Valor: " + value);
    }
}
