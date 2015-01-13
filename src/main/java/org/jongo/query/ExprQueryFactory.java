package org.jongo.query;

import com.mongodb.DBObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO description
 * Created by liusz on 15-1-13-下午2:34
 */
public class ExprQueryFactory extends QueryFactoryDecorator {

    public ExprQueryFactory(QueryFactory queryFactory) {
        super(queryFactory);
    }

    public Query createQuery(String query, Object... parameters) {
        query = parseExpr(query);
        return getQueryFactory().createQuery(query, parameters);
    }

    protected String parseExpr(String query) {
        return null;
    }

    public static void main(String[] args) {
        String query = "{a > 1 , $b = '{a \'like b}' , c> 3, d : {c > 1}}";

        Pattern pattern = Pattern
                .compile("(?!=(\\{|\\s|)+)(?<name>[A-Za-z][A-Za-z0-9]?+)\\s*(?<op>(>|=|<|>=|<=|like))\\s*(?<value>( (\'|\").+?(\'|\"))| \\d*+)(?=(}|\\s|,)+)");




        Matcher matcher = pattern.matcher(query);

        StringBuffer buffer = new StringBuffer();
        while (matcher.find()) {
            System.out.println(matcher.group());

//            System.out.println(matcher.group("name")+ " " + matcher.group("op")+ " " +matcher.group("value")  );
            //            System.out.println(matcher.group("name"));
            //            System.out.println(matcher.group("op"));
            //            System.out.println(matcher.group("value"));
            String name = matcher.group("name");
            String value = matcher.group("value");

            String opt = matcher.group("op");
            switch (opt) {
            case ">":
                matcher.appendReplacement(buffer, name + " : {\\$gt : " + value + "}");
                break;
            case "=":
                matcher.appendReplacement(buffer, name + " : " + value);
                break;
            case "<":
                matcher.appendReplacement(buffer, name + " : {\\$lt : " + value + "}");
                break;
            }
            // matcher.appendTail(buffer);
        }

        matcher.appendTail(buffer);
        System.out.println(buffer.toString());

    }
}
