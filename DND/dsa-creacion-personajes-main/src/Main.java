import characters.Clerigo;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PersonajeBuilder clerigoBuilder = new PersonajeBuilder(ClasePersonaje.Clerigo);
        Clerigo clerigo = (Clerigo) clerigoBuilder.build();
        clerigo.printInfo();


        Scanner scanner = new Scanner(System.in);


        System.out.print("value to reduce (fuerza): ");
        int fuerzaToReduce = scanner.nextInt();

        System.out.print("value to reduce (inteligencia): ");
        int inteligenciaToReduce = scanner.nextInt();

        System.out.print("value to reduce (sabiduria): ");
        int sabiduriaToReduce = scanner.nextInt();


        System.out.print("write done the code for each type of caracter 1:Clerigo, 2:Elfo, 3:Enano, 4:Guerrero, 5:Ladron, 6:Mago, 7:Mediano: ");
        int characterClass = scanner.nextInt();


        AttributeAdjuster adjuster = new AttributeAdjuster
                (
                clerigo.getFuerza(),
                clerigo.getInteligencia(),
                clerigo.getSabiduria(),
                clerigo.getDestreza(),
                clerigo.getConstitucion(),
                clerigo.getCarisma()
        );


        try {
            adjuster.adjustAttributes(fuerzaToReduce, inteligenciaToReduce, sabiduriaToReduce, characterClass);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            scanner.close();
            return; // 如果调整失败，则提前退出
        }

        // 打印调整后的角色信息
        System.out.println("\nnew value：");
        System.out.println("Fuerza: " + adjuster.getFuerza());
        System.out.println("Inteligencia: " + adjuster.getInteligencia());
        System.out.println("abiduria: " + adjuster.getSabiduria());

        // 关闭扫描器
        scanner.close();
    }
}