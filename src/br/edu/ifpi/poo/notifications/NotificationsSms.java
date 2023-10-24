package br.edu.ifpi.poo.notifications;

public class NotificationsSms implements Notifications {
    @Override
    public void enviaNotificacao(String tipo, double valor) {
        System.out.println("Enviando notificação por SMS");
        System.out.println("Tipo: " + tipo);
        System.out.println("Valor: " + valor);
    }
}
