package com.example.wantedpreonboardingchallengebackend20.Exception;

public class TransactionException extends RuntimeException {

	private TRANSACTION_ERROR transaction_error;

	public enum TRANSACTION_ERROR {
		INVALID(401, "개별물품에 대해 한번씩 구매가 가능합니다."),
		CANTCONFIRM(401, "판매승인한 제품에 대해 구매확정을 할 수 있습니다");

		private int status;
		private String msg;

		TRANSACTION_ERROR(int status, String msg) {
			this.status = status;
			this.msg = msg;
		}
	}

	public TransactionException(TRANSACTION_ERROR error){
		super(error.name());
		this.transaction_error = error;
	}

}
