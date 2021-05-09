/**
 * 
 */
package it.perk.pagopa.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Perk
 *
 */
@Data
public class ContentDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5688236515201039572L;

	/**
	 * Oggetto del messaggio.
	 * string [ 10 .. 120 ] characters 
	 */
	private String subject;
	
	/**
	 * Body del messaggio.
	 * string [ 80 .. 10000 ] characters 
	 */
	private String markdown;
	
	public ContentDTO() {
	}
	
//    public boolean checkCar(@Valid @NotNull Car car) {
//        //...
//        return false;
//    }
}
