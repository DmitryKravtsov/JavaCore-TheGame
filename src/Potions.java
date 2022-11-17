public enum Potions {
    SMALL(30,10, "small"),
    MEDIUM( 50, 18, "medium"),
    LARGE( 80, 27, "large");

    private int hitpointValue;
    private int goldValue;

    private String title;
    Potions(int hp, int gold, String title){
        this.hitpointValue = hp;
        this.goldValue = gold;
        this.title = title;
    }

    public int getHP() {
        return hitpointValue;
    }

    public int getGold() {
        return goldValue;
    }

    public String getTitle(){
        return title;
    }
}
