package com.project.emp.other;

import java.lang.reflect.Field;
import java.util.List;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import com.project.emp.dto.AbstractDto;

@Component
public class JsonPasing <T extends AbstractDto>{
	
	public String ModelOnJson(T jsonModel){
        if(jsonModel==null){
            return null;
        }
//        System.out.println("Model Name : " + jsonModel.getClass().getSimpleName());
        StringBuffer sb = new StringBuffer();
        sb.append("\"");
        sb.append(jsonModel.getClass().getSimpleName());
        sb.append("\"");
        sb.append(" : [");
        sb.append("\n");
        Field[] modelFields = jsonModel.getClass().getDeclaredFields();
        String oneData = jsonOneCreate(modelFields,jsonModel);
        if(oneData.isEmpty() || oneData == null){
            return null;
        }
        sb.append(oneData);
        sb.append("\n");
        sb.append(" ]");
        return sb.toString();
    }

    public String ModelOnJson(List<T> jsonModels){
        if(jsonModels.isEmpty() || jsonModels==null){
            return null;
        }
        System.out.println("Model Name : " + jsonModels.get(0).getClass().getSimpleName());
        StringBuffer sb = new StringBuffer();
        sb.append("\"");
        sb.append(jsonModels.get(0).getClass().getSimpleName());
        sb.append("\"");
        sb.append(" : [");
        sb.append("\n");
        for(T jsonModel : jsonModels){
            Field[] modelFields = jsonModel.getClass().getDeclaredFields();
            String oneData = jsonOneCreate(modelFields,jsonModel);
            if(oneData.isEmpty() || oneData == null){
                continue;
            }
            sb.append(oneData);
            sb.append(",");
        }
        int lastCommaIndexOf = 0;
        if((lastCommaIndexOf = sb.lastIndexOf(",")) != -1){
            sb = sb.deleteCharAt(lastCommaIndexOf);
        }
        sb.append("\n");
        sb.append(" ]");
        return sb.toString();
    }
    private String jsonOneCreate(Field[] fields,T jsonModel){
        StringBuffer sb = new StringBuffer();
        sb.append("{ ");
        for(Field field : fields){
            if(AnnotationUtils.getAnnotation(field, JsonFieldIgnore.class) != null){
                continue;
            }
            Object obj = null;
            try {
                field.setAccessible(true);
                obj = field.get(jsonModel);
            } catch (Exception e) {
                System.out.println("err");
                continue;
            }
            String jsonFieldName = field.getName();
            if(field.getType() == String.class){
                sb.append(doubleQuatationInputMessage(jsonFieldName));
                sb.append(" : ");
                String stringObj = (String) obj;
                sb.append(doubleQuatationInputMessage(stringObj));
                sb.append(", ");
            }else if(field.getType() == int.class){
                sb.append(doubleQuatationInputMessage(jsonFieldName));
                sb.append(" : ");
                Integer integerObj = (Integer) obj;
                sb.append(doubleQuatationInputMessage(integerObj.toString()));
                sb.append(", ");
            }else if(field.getType() == long.class){
                sb.append(doubleQuatationInputMessage(jsonFieldName));
                sb.append(" : ");
                Long longObj = (Long) obj;
                sb.append(doubleQuatationInputMessage(longObj.toString()));
                sb.append(", ");
            }else if(field.getType() == double.class){
                sb.append(doubleQuatationInputMessage(jsonFieldName));
                sb.append(" : ");
                Double doubleObj = (Double) obj;
                sb.append(doubleQuatationInputMessage(doubleObj.toString()));
                sb.append(", ");
            }else if(field.getType() == boolean.class){
                sb.append(doubleQuatationInputMessage(jsonFieldName));
                sb.append(" : ");
                Boolean booleanObj = (Boolean) obj;
                sb.append(doubleQuatationInputMessage(booleanObj.toString()));
                sb.append(", ");
            }
            continue;
        }
        int lastCommaIndexOf = sb.lastIndexOf(",");
        if((lastCommaIndexOf = sb.lastIndexOf(",")) != -1){
            sb = sb.deleteCharAt(lastCommaIndexOf);
        }
        sb.append(" }");
        return sb.toString();
    }
    
    private String doubleQuatationInputMessage(String message){
        return "\""+message+"\"";
    }
}
