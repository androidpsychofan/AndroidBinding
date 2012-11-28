package gueei.binding.viewAttributes.adapterView;

import gueei.binding.BindingType;
import gueei.binding.Utility;
import gueei.binding.ViewAttribute;
import gueei.binding.viewAttributes.templates.Layout;
import gueei.binding.viewAttributes.templates.SingleTemplateLayout;
import android.view.View;

/**
 * Item Template of Adapter View
 * Template should be specified as Layout, you can bind to Integer value or 
 * {@literal @}layout/main_layout
 * 
 * Due to the complexity of this API, we recommend to switch to binding:adapter instead of this
 * e.g.  binding:adapter="ADAPTER({source=..., template=...})
 * which will give more fine-grain control on adapter generated
 * 
 * @name itemSource
 * @widget AdapterView
 * @type gueei.binding.viewAttributes.templates.Layout
 * 
 * @accepts	gueei.binding.viewAttributes.templates.Layout
 * @accepts Integer
 * @accepts CharSequence
 * 
 * @category list
 * @related 
 * 
 * @author andy
 */
public class ItemTemplateViewAttribute extends ViewAttribute<View, Layout> {

	public ItemTemplateViewAttribute(View view,
			String attributeName) {
		super(Layout.class, view, attributeName);
	}

	private Layout template;
	
	@Override
	protected void doSetAttributeValue(Object newValue) {
		if(getView()==null) return;
		if (newValue instanceof Layout){
			template = (Layout)newValue;
			return;
		}else if (newValue instanceof Integer){
			if ((Integer)newValue>0)
				template = new SingleTemplateLayout((Integer)newValue);
			return;
		}else if (newValue instanceof CharSequence){
			int value = Utility.resolveLayoutResource(newValue.toString(), getView().getContext());
			if (value>0){
				template = new SingleTemplateLayout((Integer)newValue);
			}
			return;
		}
	}

	@Override
	public Layout get() {
		return template;
	}

	@Override
	protected BindingType AcceptThisTypeAs(Class<?> type) {
		if (Integer.class.isAssignableFrom(type) ||
			CharSequence.class.isAssignableFrom(type)) 
			return BindingType.OneWay;
		if (Layout.class.isAssignableFrom(type)) return BindingType.TwoWay;
		return BindingType.NoBinding;
	}
}
