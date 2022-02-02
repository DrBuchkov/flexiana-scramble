(ns com.flexiana.core
  (:require ["@chakra-ui/react" :as chakra]
            [day8.re-frame.http-fx]
            [ajax.edn :refer [edn-request-format edn-response-format]]
            [reagent.dom :as rd]
            [re-frame.core :as rf]
            [fork.re-frame :as fork]
            [potpuri.core :as pt]))

(rf/reg-event-fx
  ::scramble-request
  (fn [_ [_ {:keys [letters word]}]]
    {:http-xhrio {:method          :get
                  :uri             "http://localhost:8080/api/scramble"
                  :timeout         5000
                  :params          (pt/map-of letters word)
                  :format          (edn-request-format)
                  :response-format (edn-response-format)
                  :on-success      [::scramble-success]
                  :on-failure      [::scramble-fail]}}))

(rf/reg-event-db
  ::scramble-success
  (fn [db [_ data]]
    (assoc db :scramble data)))

(rf/reg-event-db
  ::scramble-fail
  (fn [db _] db))

(rf/reg-sub
  :scramble
  (fn [db _]
    (:scramble db)))

(defn form [{:keys [form-id values handle-change handle-submit]}]
  [:form {:id form-id :on-submit handle-submit}
   [:> chakra/FormControl
    [:> chakra/FormLabel "Letters"]
    [:> chakra/Input {:name      :letters
                      :value     (values :letters)
                      :as        :input
                      :on-change handle-change}]]
   [:> chakra/FormControl
    [:> chakra/FormLabel "Word"]
    [:> chakra/Input {:name      :word
                      :value     (values :word)
                      :as        :input
                      :on-change handle-change}]]
   [:> chakra/ButtonGroup {:mt :5px}
    [:> chakra/Button {:type :submit} "Scramble?"]]])

(defn app []
  (let [{:keys [result letters word]} @(rf/subscribe [:scramble])]
    [:> chakra/ChakraProvider
     [:> chakra/Center {:height :100vh}
      [:> chakra/VStack
       [:> chakra/Heading "Scramble!"]
       [:> chakra/Heading {:size :lg} "Check if your word can be produced by rearranging the letters"]
       [fork/form {:initial-values   {:letters "" :word ""}
                   :form-id          "scramble"
                   :prevent-default? true
                   :keywordize-keys  true
                   :on-submit        (fn [{:keys [values]}]
                                       (rf/dispatch [::scramble-request values]))}
        form]
       (when result
         (let [msg (case result
                     true "is"
                     false "is not")]
           [:> chakra/Text (str "The word \"" word "\" " msg " reproducible from the letters \"" letters "\"")]))]]]))

(defn ^:dev/after-load mount! []
  (rf/clear-subscription-cache!)
  (rd/render [app] (.getElementById js/document "app")))
