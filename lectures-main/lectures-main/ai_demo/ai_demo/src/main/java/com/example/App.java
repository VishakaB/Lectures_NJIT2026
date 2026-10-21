package com.example;
import de.kherud.llama.InferenceParameters;
import de.kherud.llama.LlamaModel;
import de.kherud.llama.ModelParameters;

public class App 
{
private static final String MODEL_PATH = System.getenv().getOrDefault("MODEL_FILE",
            "qwen2.5-3b-instruct-q4_k_m.gguf");
    public static void main( String[] args ) {
  
        try {
            String user_prompt = "Why is Java a robust programming language?";
            String system_prompt = """
                    ### System:
                    Write one short informative sentence based on the requirements and User prompt below.
                    Requirements:
                    - Use polite words
                    - Use professional speaking styles
                    - Give background information about the topic
                    - Use simple words
                    - limit to 100 words

                    User prompt
                    %s
                    ### Assistant:
                    """.formatted(user_prompt);

            ModelParameters modelParameters =
                    new ModelParameters()
                            .setModel(MODEL_PATH)
                            .disableLog();
            
            try (LlamaModel model = new LlamaModel(modelParameters)) {
                InferenceParameters inferenceParameters =
                        new InferenceParameters(system_prompt)
                            .setNPredict(-1)
                            .setTemperature(0.5f)
                            .setTopK(40)
                            .setTopP(0.9f);

                String response = model.complete(inferenceParameters);
                System.out.println("AI Generated message: " + response);
            } catch (Exception e) {
                System.err.println("e: "+ e);
            }                    
        } catch (Exception err){
            System.err.println("err: "+err);
        }
    }

    

}
