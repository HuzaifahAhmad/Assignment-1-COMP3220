import java.util.*;

abstract class CShape {
    private static int idCounter = 1;
    protected int id;

    public CShape() {
        this.id = idCounter++;
    }

    public abstract String getType();

    public abstract String getDimensions();
}

class CCanvas {
    private List<CShape> shapes;

    public CCanvas() {
        shapes = new ArrayList<>();
        generateRandomShapes();
    }

    private void generateRandomShapes() {
        Random rand = new Random();
        List<Class<? extends CShape>> shapeTypes = List.of(CRectangle.class, CSquare.class, CCircle.class, COval.class);

        while (shapes.size() < 10) {
            Class<? extends CShape> shapeType = shapeTypes.get(rand.nextInt(shapeTypes.size()));
            CShape newShape = createRandomShape(rand, shapeType);

            if (!shapes.contains(newShape)) {
                shapes.add(newShape);
            }
        }
    }

    private CShape createRandomShape(Random rand, Class<? extends CShape> shapeType) {
        if (shapeType == CRectangle.class) {
            return new CRectangle(rand.nextInt(100) + 1, rand.nextInt(100) + 1);
        } else if (shapeType == CSquare.class) {
            int side = rand.nextInt(100) + 1;
            return new CSquare(side);
        } else if (shapeType == CCircle.class) {
            return new CCircle(rand.nextInt(100) + 1);
        } else { // shapeType == COval.class
            int horizontalRadius = rand.nextInt(100) + 1;
            int verticalRadius = rand.nextInt(100) + 1;
            return new COval(horizontalRadius, verticalRadius);
        }
    }

    public void displayShapes() {
        System.out.println("Canvas has the following random shapes:\n");
        for (CShape shape : shapes) {
            System.out.println("Shape " + shape.id + ": " + shape.getType() + " " + shape.getDimensions() + "\n");
        }
    }
}

class COval extends CShape {
    private int horizontalRadius;
    private int verticalRadius;

    public COval(int horizontalRadius, int verticalRadius) {
        super();
        this.horizontalRadius = horizontalRadius;
        this.verticalRadius = verticalRadius;
    }

    @Override
    public String getType() {
        return "OVAL";
    }

    @Override
    public String getDimensions() {
        return horizontalRadius + "x" + verticalRadius;
    }
}

class CCircle extends COval {
    public CCircle(int radius) {
        super(radius, radius);
    }

    @Override
    public String getType() {
        return "CIRCLE";
    }
}

class CRectangle extends CShape {
    private int length;
    private int width;

    public CRectangle(int length, int width) {
        super();
        this.length = length;
        this.width = width;
    }

    @Override
    public String getType() {
        return "RECTANGLE";
    }

    @Override
    public String getDimensions() {
        return length + "x" + width;
    }
}

class CSquare extends CRectangle {
    public CSquare(int side) {
        super(side, side);
    }

    @Override
    public String getType() {
        return "SQUARE";
    }
}

public class Main {
    public static void main(String[] args) {
        CCanvas canvas = new CCanvas();
        canvas.displayShapes();
    }
}
