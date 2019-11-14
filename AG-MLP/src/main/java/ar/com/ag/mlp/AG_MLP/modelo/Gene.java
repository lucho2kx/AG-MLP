package ar.com.ag.mlp.AG_MLP.modelo;

public class Gene {

	private float wb, w1, w2, w3, w4, w5, w6, w7, ws;

	public Gene(float wb, float w1, float w2, float w3, float w4, float w5, float w6, float w7, float ws) {
		super();
		this.wb = wb;
		this.w1 = w1;
		this.w2 = w2;
		this.w3 = w3;
		this.w4 = w4;
		this.w5 = w5;
		this.w6 = w6;
		this.w7 = w7;
		this.ws = ws;
	}

	public Gene() {
		super();		
	}

	public float getWb() {
		return wb;
	}

	public void setWb(float wb) {
		this.wb = wb;
	}

	public float getW1() {
		return w1;
	}

	public void setW1(float w1) {
		this.w1 = w1;
	}

	public float getW2() {
		return w2;
	}

	public void setW2(float w2) {
		this.w2 = w2;
	}

	public float getW3() {
		return w3;
	}

	public void setW3(float w3) {
		this.w3 = w3;
	}

	public float getW4() {
		return w4;
	}

	public void setW4(float w4) {
		this.w4 = w4;
	}

	public float getW5() {
		return w5;
	}

	public void setW5(float w5) {
		this.w5 = w5;
	}

	public float getW6() {
		return w6;
	}

	public void setW6(float w6) {
		this.w6 = w6;
	}

	public float getW7() {
		return w7;
	}

	public void setW7(float w7) {
		this.w7 = w7;
	}

	public float getWs() {
		return ws;
	}

	public void setWs(float ws) {
		this.ws = ws;
	}

	@Override
	public String toString() {
		return "Gene [wb=" + wb + ", w1=" + w1 + ", w2=" + w2 + ", w3=" + w3 + ", w4=" + w4 + ", w5=" + w5 + ", w6="
				+ w6 + ", w7=" + w7 + ", ws=" + ws + "]";
	}
	
}
