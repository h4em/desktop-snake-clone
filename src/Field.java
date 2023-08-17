public class Field {
    //TODO: czy to dobrze ze te atrybuty sa publiczne?
    public int x;

    public int y;

    public Field(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null || this.getClass() != obj.getClass())
            return false;

        Field field = (Field) obj;
        return (this.x == field.x && this.y == field.y);
    }


    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}

