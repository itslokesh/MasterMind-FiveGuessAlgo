package mastermindhard;
public class Ball {
    private char color;
    
    Ball() {
        this.color = 0;
    }
    Ball(char colo){
        this.color=colo;
    }
    Ball(char colo, int posi) {
        this.color = colo;
    }

    public char getColor() {
        return this.color;
    }

    public void setColor(char color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Ball{" + "color=" + color + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.color;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ball other = (Ball) obj;
        if (this.color != other.color) {
            return false;
        }
        return true;
    }

}
