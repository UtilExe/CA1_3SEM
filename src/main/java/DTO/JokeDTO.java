
package DTO;

import entities.Joke;
import entities.Members;

public class JokeDTO {
    

    private Long Id;
    private String joke;
    private String reference;
    private String type;
    
    public JokeDTO(Joke joke) {
        this.Id = joke.getId();
        this.joke = joke.getJoke();
        this.reference = joke.getReference();
        this.type = joke.getType();
    }

    public Long getId() {
        return Id;
    }

    public String getJoke() {
        return joke;
    }

    public String getReference() {
        return reference;
    }

    public String getType() {
        return type;
    }
    
}
