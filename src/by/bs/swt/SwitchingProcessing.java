package by.bs.swt;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class SwitchingProcessing {
	private int CicleNumber;
	private boolean CicleCheck;
	private Display display;
	private Shell shell;
	private List<Shell> shells;

	public SwitchingProcessing(Display display, Shell shell, List<Shell> shells) {
		this.display = display;
		this.shell = shell;
		this.shells = shells;
		this.CicleNumber = 0;
		this.CicleCheck = false;
	}

	public void displayProcessing() {

		Thread engin = new Thread(processingStream(), "MyThread");
		engin.start();

		display.addFilter(SWT.KeyDown, new Listener() {
			public void handleEvent(Event event) {
				if ((event.stateMask & SWT.CTRL) != 0 & event.keyCode == 114)
					CicleCheck = true;

				if ((event.stateMask & SWT.CTRL) != 0 & event.keyCode == 115)
					CicleCheck = false;
			}
		});

	}

	private Runnable processingStream() {
		Runnable r = () -> {
			while (true) {
				try {
					if (CicleCheck == true & CicleNumber == 5) {
						display.asyncExec(new Runnable() {
							@Override
							public void run() {
								shells.forEach(x -> x.setVisible(false));
								shell.setVisible(true);
								CicleNumber++;

							}
						});
					}
					Thread.sleep(1000);

					if (CicleCheck == true & CicleNumber == 6) {
						display.asyncExec(new Runnable() {
							@Override
							public void run() {
								shell.setVisible(false);
								CicleNumber = 0;
							}
						});
					}

					Thread.sleep(1000);

					if (CicleCheck == true & CicleNumber != 5) {
						for (; CicleNumber < 5;) {
							display.asyncExec(new Runnable() {
								@Override
								public void run() {
									if (CicleCheck == true) {
										shells.get(CicleNumber).setVisible(true);
										CicleNumber++;
									}
								}
							});
							Thread.sleep(1000);
						}
					}

				} catch (InterruptedException e) {
				}
			}
		};
		return r;
	}
}
