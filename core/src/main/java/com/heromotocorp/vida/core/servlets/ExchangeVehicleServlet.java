package com.heromotocorp.vida.core.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;

import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.Rendition;
import com.google.gson.Gson;
import com.heromotocorp.vida.core.config.ClientConfig;
import com.heromotocorp.vida.core.config.GlobalConfig;
import com.heromotocorp.vida.core.vo.Brand;
import com.heromotocorp.vida.core.vo.ExchangeVehicleVO;
import com.heromotocorp.vida.core.vo.ModelVO;

/**
 * The Class ExchangeVehicleServlet.
 */
@Component(service = { Servlet.class })
@SlingServletResourceTypes(resourceTypes = "vida/components/page", methods = HttpConstants.METHOD_GET, selectors = "exchange-vehicle", extensions = "json")
@ServiceDescription("Exchange Vehicle Servlet")
public class ExchangeVehicleServlet extends SlingSafeMethodsServlet {

	/** The Constant CONTENT_DAM_VIDA_CONFIG_EXCHANGE_VEHICLE_CSV. */
	public static final String CONTENT_DAM_VIDA_CONFIG_EXCHANGE_VEHICLE_CSV = "/content/dam/vida/config/master-brand-list.csv";

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3592470025905082741L;

	/** The Constant COMMA_DELIMITER. */
	private static final String COMMA_DELIMITER = ",";

	/** The global config. */
	@Reference
	private transient GlobalConfig globalConfig;

	/** The client config. */
	@Reference
	private transient ClientConfig clientConfig;

	/**
	 * Do get.
	 *
	 * @param req  the req
	 * @param resp the resp
	 * @throws ServletException the servlet exception
	 * @throws IOException      Signals that an I/O exception has occurred.
	 */
	@Override
	protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp)
			throws ServletException, IOException {

		String location = globalConfig.exchangeVehicleCSVLocationReqPath() == null
				? CONTENT_DAM_VIDA_CONFIG_EXCHANGE_VEHICLE_CSV
				: globalConfig.exchangeVehicleCSVLocationReqPath();

		List<Brand> brands = new ArrayList<>();
		Resource assetResource = req.getResourceResolver().getResource(location);
		Asset asset = assetResource.adaptTo(Asset.class);
		Rendition original = asset.getOriginal();
		if (original != null) {

			InputStream stream = original.getStream();
			List<String[]> records = new ArrayList<>();
			try (java.util.Scanner scanner = new java.util.Scanner(stream);) {
				while (scanner.hasNextLine()) {
					records.add(getRecordFromLine(scanner.nextLine()));
				}
				records.remove(0);
				for (String[] csvcolumn : records) {
					createJson(brands, csvcolumn);
				}
			}
			String jsonString = new Gson().toJson(brands);
			resp.getWriter().write(jsonString);
		}
	}

	/**
	 * Creates the json.
	 *
	 * @param brands    the brands
	 * @param csvcolumn the csvcolumn
	 */
	private void createJson(List<Brand> brands, String[] csvcolumn) {
		if (!brands.isEmpty()) {
			Brand availableBrand = findBrand(brands, csvcolumn[0]);
			if (Objects.nonNull(availableBrand)) {
				List<ExchangeVehicleVO> brandCategoryList = availableBrand.getBrandCategory();
				ExchangeVehicleVO availableCategogy = findCategory(brandCategoryList, csvcolumn[1]);
				if (Objects.nonNull(availableCategogy)) {
					ModelVO model = new ModelVO();
					model.setLabel(csvcolumn[2]);
					model.setValue(csvcolumn[2]);
					availableCategogy.getBrandModel().add(model);
					ModelVO ccObj = findCC(availableCategogy.getCcList(), csvcolumn[3]);
					if (Objects.isNull(ccObj)) {
						ModelVO cc = new ModelVO();
						cc.setLabel(csvcolumn[3]);
						cc.setValue(csvcolumn[3]);
						availableCategogy.getCcList().add(cc);
					}
				} else {
					ExchangeVehicleVO vo = new ExchangeVehicleVO();
					vo.setName(csvcolumn[1]);
					vo.setNameValue(csvcolumn[1]);
					List<ModelVO> brandModelList = new ArrayList<>();
					ModelVO model = new ModelVO();
					model.setLabel(csvcolumn[2]);
					model.setValue(csvcolumn[2]);
					brandModelList.add(model);
					List<ModelVO> ccList = new ArrayList<>();
					ModelVO cc = new ModelVO();
					cc.setLabel(csvcolumn[3]);
					cc.setValue(csvcolumn[3]);
					ccList.add(cc);
					vo.setBrandModel(brandModelList);
					vo.setCcList(ccList);
					brandCategoryList.add(vo);
				}
			} else {
				Brand brandVO = new Brand();
				List<ExchangeVehicleVO> exchangeVehicleVO = new ArrayList<>();
				ExchangeVehicleVO exchangeVehicleVOObj = new ExchangeVehicleVO();
				String brandName = csvcolumn[0];
				exchangeVehicleVOObj.setName(csvcolumn[1]);
				exchangeVehicleVOObj.setNameValue(csvcolumn[1]);
				List<ModelVO> brandModelList = new ArrayList<>();
				ModelVO model = new ModelVO();
				model.setLabel(csvcolumn[2]);
				model.setValue(csvcolumn[2]);
				brandModelList.add(model);
				List<ModelVO> ccList = new ArrayList<>();
				ModelVO cc = new ModelVO();
				cc.setLabel(csvcolumn[3]);
				cc.setValue(csvcolumn[3]);
				ccList.add(cc);
				exchangeVehicleVOObj.setBrandModel(brandModelList);
				exchangeVehicleVOObj.setCcList(ccList);
				exchangeVehicleVO.add(exchangeVehicleVOObj);
				brandVO.setBrandName(brandName);
				brandVO.setBrandValue(brandName);
				brandVO.setBrandCategory(exchangeVehicleVO);
				brands.add(brandVO);
			}
		} else {
			Brand brandVO = new Brand();
			List<ExchangeVehicleVO> exchangeVehicleVO = new ArrayList<>();
			ExchangeVehicleVO vo = new ExchangeVehicleVO();
			String brandName = csvcolumn[0];
			vo.setName(csvcolumn[1]);
			vo.setNameValue(csvcolumn[1]);
			List<ModelVO> brandModelList = new ArrayList<>();
			ModelVO model = new ModelVO();
			model.setLabel(csvcolumn[2]);
			model.setValue(csvcolumn[2]);
			brandModelList.add(model);
			List<ModelVO> ccList = new ArrayList<>();
			ModelVO cc = new ModelVO();
			cc.setLabel(csvcolumn[3]);
			cc.setValue(csvcolumn[3]);
			ccList.add(cc);
			vo.setBrandModel(brandModelList);
			vo.setCcList(ccList);
			exchangeVehicleVO.add(vo);
			brandVO.setBrandName(brandName);
			brandVO.setBrandValue(brandName);
			brandVO.setBrandCategory(exchangeVehicleVO);
			brands.add(brandVO);
		}
	}

	/**
	 * Find CC.
	 *
	 * @param ccList the cc list
	 * @param cc     the cc
	 * @return the model VO
	 */
	private ModelVO findCC(List<ModelVO> ccList, String cc) {
		return ccList.stream().filter(category -> cc.equals(category.getValue())).findFirst().orElse(null);

	}

	/**
	 * Find category.
	 *
	 * @param brandCategoryList the brand category list
	 * @param categoryName      the category name
	 * @return the exchange vehicle VO
	 */
	public ExchangeVehicleVO findCategory(List<ExchangeVehicleVO> brandCategoryList, String categoryName) {
		return brandCategoryList.stream().filter(category -> categoryName.equals(category.getName())).findFirst()
				.orElse(null);

	}

	/**
	 * Find brand.
	 *
	 * @param listBrand the list brand
	 * @param brandName the brand name
	 * @return the brand
	 */
	public Brand findBrand(Collection<Brand> listBrand, String brandName) {
		return listBrand.stream().filter(brand -> brandName.equals(brand.getBrandName())).findFirst().orElse(null);
	}

	/**
	 * Gets the record from line.
	 *
	 * @param line the line
	 * @return the record from line
	 */
	private static String[] getRecordFromLine(String line) {
		List<String> values = new ArrayList<>();
		try (Scanner rowScanner = new Scanner(line)) {
			rowScanner.useDelimiter(COMMA_DELIMITER);
			while (rowScanner.hasNext()) {
				values.add(rowScanner.next());
			}
		}
		return Arrays.copyOf(values.toArray(), values.toArray().length, String[].class);
	}

}
