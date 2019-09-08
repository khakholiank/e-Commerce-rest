
package com.rgu.ecommerce.model;

/**
 *
 * @author Nikit Khakholia
 */
public class Rating {
    private int id;
    private int score;
    private int description;

   
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getDescription() {
        return description;
    }

    public void setDescription(int description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
}
