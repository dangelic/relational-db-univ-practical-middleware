package uni.dbprak21.shopmiddleware;

public interface InitFinish {
    // Initialisiert die Middleware, erstellt die Datenbankverbindung und führt notwendige Initialisierungsschritte durch.
    default void init() {};

    // Wird bei Beendigung der Anwendung aufgerufen, um Ressourcen freizugeben, insbesondere Datenbankobjekte.
    default void finish() {};
}
