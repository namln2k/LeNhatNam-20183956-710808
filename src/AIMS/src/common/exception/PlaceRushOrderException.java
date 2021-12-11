package common.exception;

/**
 * Exception được throw nếu giỏ hàng chứa bất kỳ sản phẩm nào không support rush
 * order mà khách hàng vẫn chọn place rush order
 * 
 * @author NAM.LN183956
 *
 */
public class PlaceRushOrderException extends AimsException {

	private static final long serialVersionUID = -1739660027768424211L;

	public PlaceRushOrderException() {

	}

	public PlaceRushOrderException(String message) {
		super(message);
	}

}
