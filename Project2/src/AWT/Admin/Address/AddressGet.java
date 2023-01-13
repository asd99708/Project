package AWT.Admin.Address;

import java.net.URLEncoder;
import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

public class AddressGet {
	static ArrayList<AddressVo> addr = new ArrayList<>();

	public static String getTagvalue(String tag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
		Node nValue = (Node) nlList.item(0);
		if (nValue == null) {
			return null;
		}
		return nValue.getNodeValue();
	}

	public static void addressGet(String search) {
		int page = 1;
		try {
			while (true) {
				String ppage = Integer.toString(page);
				StringBuilder urlBuilder = new StringBuilder(
						"http://openapi.epost.go.kr/postal/retrieveNewAdressAreaCdSearchAllService/retrieveNewAdressAreaCdSearchAllService/getNewAddressListAreaCdSearchAll"); /*
																																												 * URL
																																												 */

				urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
						+ "=1BAw28%2BXXXy7CS0qWNkYIoJP%2FjNlCe2wbJdumUyCOmnfVjYjiFiZV%2F6hsxZYeBRB%2BRHTCA7%2BdFOZkfhKsYKamw%3D%3D");

				urlBuilder.append("&" + URLEncoder.encode("srchwrd", "UTF-8") + "="
						+ URLEncoder.encode(search, "UTF-8")); /* 검색어 */
				urlBuilder.append("&" + URLEncoder.encode("countPerPage", "UTF-8") + "="
						+ URLEncoder.encode("50", "UTF-8")); /* 페이지당 출력될 개수를 지정(최대50) */
				urlBuilder.append("&" + URLEncoder.encode("currentPage", "UTF-8") + "="
						+ URLEncoder.encode(ppage, "UTF-8")); /* 출력될 페이지 번호 */

				String url = urlBuilder.toString();
				System.out.println(url);
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(url);

				doc.getDocumentElement().normalize();
				NodeList nList = doc.getElementsByTagName("newAddressListAreaCdSearchAll");
				System.out.println(nList.getLength());
				for (int temp = 0; temp < nList.getLength(); temp++) {
					Node nNode = nList.item(temp);
					AddressVo cv = null;

					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) nNode;
						cv = new AddressVo(getTagvalue("zipNo", eElement), getTagvalue("lnmAdres", eElement),
								getTagvalue("rnAdres", eElement));
						addr.add(cv);
					}
				}
				if (nList.getLength() < 50) {
					break;
				}

				page += 1;

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}