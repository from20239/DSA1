public class AttributeAdjuster {
    private int fuerza;
    private int inteligencia;
    private int sabiduria;
    private int destreza;
    private int constitucion;
    private int carisma;

    // basic info
    public AttributeAdjuster(int fuerza, int inteligencia, int sabiduria, int destreza, int constitucion, int carisma) {
        this.fuerza = fuerza;
        this.inteligencia = inteligencia;
        this.sabiduria = sabiduria;
        this.destreza = destreza;
        this.constitucion = constitucion;
        this.carisma = carisma;
    }

    // changing info
    public void adjustAttributes(int fuerzaToReduce, int inteligenciaToReduce, int sabiduriaToReduce, int characterClass) {
        // check if its possible
        if (!canReduce(fuerzaToReduce, inteligenciaToReduce, sabiduriaToReduce, characterClass)) {
            throw new IllegalArgumentException("cannot reduce, some ability will be below than 9。");
        }

        // total reduction
        int totalReduction = fuerzaToReduce + inteligenciaToReduce + sabiduriaToReduce;

        // 根据减少的属性更新主要属性
        if (fuerzaToReduce > 0) {
            fuerza -= fuerzaToReduce;
        }
        if (inteligenciaToReduce > 0) {
            inteligencia -= inteligenciaToReduce;
        }
        if (sabiduriaToReduce > 0) {
            sabiduria -= sabiduriaToReduce;
        }

        // 每减少两个点可以增加一个主要属性
        int increase = totalReduction / 2;

        // set main value for each type
        //1:Clerigo, 2:Elfo, 3:Enano, 4:Guerrero, 5:Ladron, 6:Mago, 7:Mediano
        switch (characterClass) {
            case 2:
                inteligencia += increase; // 例子：增加智力
                break;
            case 3:
                constitucion += increase; // 例子：增加体质
                break;
            case 7:
                constitucion += increase; // 增加体质
                sabiduria += increase; // 增加感知
                break;
            case 6:
                // 魔法师没有特定要求
                break;
            // 可以添加更多的角色类型处理
        }
    }

    // 检查是否可以减少属性
    //1:Clerigo, 2:Elfo, 3:Enano, 4:Guerrero, 5:Ladron, 6:Mago, 7:Mediano
    private boolean canReduce(int fuerzaToReduce, int inteligenciaToReduce, int sabiduriaToReduce, int characterClass) {
        boolean canReduceFuerza = (fuerza - fuerzaToReduce >= 9);
        boolean canReduceInteligencia = (inteligencia - inteligenciaToReduce >= 9);
        boolean canReduceSabiduria = (sabiduria - sabiduriaToReduce >= 9);

        switch (characterClass) {
            case 2:
                return canReduceFuerza && canReduceInteligencia && canReduceSabiduria && (inteligencia >= 9);
            case 3:
                return canReduceFuerza && canReduceInteligencia && canReduceSabiduria && (constitucion >= 9);
            case 7:
                return canReduceFuerza && canReduceInteligencia && canReduceSabiduria && (constitucion >= 9 && destreza >= 9);
            case 6:
                return canReduceFuerza && canReduceInteligencia && canReduceSabiduria; // 魔法师没有特定要求
            default:
                return false; // 其他未定义角色类型返回false
        }
    }

    // 获取当前属性值的方法
    public int getFuerza() {
        return fuerza;
    }

    public int getInteligencia() {
        return inteligencia;
    }

    public int getSabiduria() {
        return sabiduria;
    }

    public int getDestreza() {
        return destreza;
    }

    public int getConstitucion() {
        return constitucion;
    }

    public int getCarisma() {
        return carisma;
    }
}