package models;


public class MorbidityGroup {
    private static Long totalIds;
    private static Long id;
    private String name;


    public  MorbidityGroup(String name){

        this.totalIds = this.totalIds ==null?1L:this.totalIds +1L;
        this.id = this.totalIds;
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }


}
