import java.awt.Cursor;

import com.gviktor.grafika.view.DefinePolyCanvas;
import com.gviktor.grafika.view.InterpolateCanvas;
import com.gviktor.grafika.view.MainFrame;
import com.gviktor.grafika.view.TriangleCanvas;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TriangleCanvas canvasTrian = new TriangleCanvas();
		DefinePolyCanvas canvasPoly= new DefinePolyCanvas();
		InterpolateCanvas convasInter = new InterpolateCanvas();
		MainFrame mainFrame = new MainFrame(convasInter);
		mainFrame.setCursor( Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
	}

}
