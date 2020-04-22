package by.bs.swt;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Linker {
	private Display display;
	private Shell shell;
	private List<Shell> shells;

	Linker() {
	}

	public void run() {
		display = new Display();
		shell = new Shell(display);
		shell.setText("SWT");
		shell.setSize(700, 700);

		RowLayout rowLayout = new RowLayout();
		rowLayout.spacing = 50;
		rowLayout.marginLeft = 10;
		rowLayout.marginTop = 10;

		shell.setLayout(rowLayout);

		// Expansion of the interface
		List<GraficBuffer> linkers = new ArrayList<>();
		linkers.add(new Save());
		linkers.add(new Swap());
		linkers.add(new RadioCheck());
		linkers.add(new CheckCheck());
		linkers.add(new MoveTable());

		shells = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			shells.add(new Shell(display));
			shells.get(i).setLayout(rowLayout);
			shells.get(i).setText("SWT " + (i + 1));
			shells.get(i).setSize(400, 400);

			linkers.get(i).run(shells.get(i));
			linkers.get(i).run(shell);

			shells.get(i).open();
			shells.get(i).setVisible(false);
		}
		shell.open();

		// Beginning of processing of switching
		SwitchingProcessing processing = new SwitchingProcessing(display, shell, shells);
		processing.displayProcessing();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}

		}
		display.dispose();
	}

}
