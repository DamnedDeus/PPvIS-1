package by.bs.swt;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.RowData;

public class CheckCheck extends GraficBuffer {
	CheckCheck() {
	}

	@Override
	protected void processing(Composite group, MessageBox errorMessege) {

		errorMessege.setMessage("This item is already active");

		// Buttons
		Button button = new Button(group, SWT.PUSH);
		button.setText("Check text");
		button.pack();

		Group GroupCheck = new Group(group, SWT.NONE);
		GroupCheck.setLayout(new RowLayout(SWT.HORIZONTAL));

		List<Button> buttons = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			Button button1 = new Button(GroupCheck, SWT.CHECK);
			button1.setText("Check " + (i + 1));
			buttons.add(button1);
		}

		// Text
		Text text = new Text(group, SWT.BORDER);
		RowData layoutData = new RowData();
		layoutData.width = 150;
		text.setLayoutData(layoutData);
		text.setText("");
		text.setSize(20, 50);
		text.pack();

		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				for (int i = 0; i < buttons.size(); i++) {
					if (text.getText().equals("Check " + (i + 1))) {
						if (buttons.get(i).getSelection() == true)
							buttons.get(i).setSelection(false);
						else if (buttons.get(i).getSelection() == false)
							buttons.get(i).setSelection(true);

						break;
					} else if (i == buttons.size() - 1)
						errorMessege.open();
				}

				errorMessege.open();
			}
		});

	}
}
