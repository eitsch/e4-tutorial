package org.eclipse.e4.tutorial.contacts.views.details;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.tutorial.contacts.model.Contact;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class DetailsView {

	private final DataBindingContext dbc = new DataBindingContext();
	private final WritableValue contactValue = new WritableValue();

	@Inject
	public DetailsView(Composite parent) {
		final Composite composite = new Composite(parent, SWT.NONE);
		composite
				.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		composite.setLayout(new GridLayout(2, false));

		createText(composite, "First Name:", "firstName");
		createText(composite, "Last Name:", "lastName");
		createText(composite, "Email:", "email");
	}

	private Text createText(final Composite parent, final String labelText,
			final String property) {
		final Label label = new Label(parent, SWT.NONE);
		label.setText(labelText);
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		label.setLayoutData(gridData);

		final Text text = new Text(parent, SWT.NONE);
		GridData gridData2 = new GridData(GridData.FILL_HORIZONTAL);
		gridData2.horizontalIndent = 0;
		gridData2.horizontalSpan = 1;
		text.setLayoutData(gridData2);

		if (property != null) {
			dbc.bindValue(SWTObservables.observeText(text, SWT.Modify),
					PojoObservables.observeDetailValue(contactValue, property,
							String.class));
		}

		return text;
	}
}
